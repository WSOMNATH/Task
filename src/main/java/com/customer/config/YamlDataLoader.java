//package com.customer.config;
//import com.customer.model.Customer;
//import com.customer.repo.CustomerRepository;
//import com.fasterxml.jackson.databind.ObjectMapper;
//	import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
//	import jakarta.annotation.PostConstruct;
//    import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//	import java.io.InputStream;
//	import java.util.List;
//	import java.util.Map;
//
//	@Component
//	public class YamlDataLoader {
//		
//@Autowired(required = true)
//CustomerRepository customerRepository;
//	    private final CustomerRepository customerRepository;
//
////	    public YamlDataLoader(CustomerRepository customerRepository) {
////	        this.customerRepository = customerRepository;
////	    }
//
//	    @PostConstruct
//	    public void loadData() throws Exception {
//	        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//	        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("customers.yml");
//
//	        Map<String, List<Customer>> data = mapper.readValue(inputStream, Map.class);
//
//	        // Use a new ObjectMapper to convert the generic map to List<Customer>
//	        ObjectMapper convertMapper = new ObjectMapper();
//	        List<Customer> customers = convertMapper.convertValue(
//	                data.get("customers"),
//	                convertMapper.getTypeFactory().constructCollectionType(List.class, Customer.class)
//	        );
//
//	        customerRepository.saveAll(customers);
//	    }
//	}
//
//
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

