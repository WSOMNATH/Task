
package com.customer.config;

import com.customer.model.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class YamlDataLoader {

    private List<Customer> customers = Collections.emptyList();

    public List<Customer> getCustomers() {
        return customers;
    }

    @PostConstruct
    public void loadData() {
        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("customers.yml");

            if (inputStream == null) {
                throw new RuntimeException("customers.yml not found in classpath.");
            }

            Map<String, List<Customer>> data = mapper.readValue(inputStream, Map.class);

            ObjectMapper convertMapper = new ObjectMapper();
            customers = convertMapper.convertValue(
                    data.get("customers"),
                    convertMapper.getTypeFactory().constructCollectionType(List.class, Customer.class)
            );

        } catch (Exception e) {
            e.printStackTrace();
            customers = Collections.emptyList();
        }
    }
}

