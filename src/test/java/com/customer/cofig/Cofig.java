package com.customer.cofig;


import com.customer.config.YamlDataLoader;
import com.customer.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class YamlDataLoaderTest {

    private YamlDataLoader yamlDataLoader;

    @BeforeEach
    void setUp() {
        yamlDataLoader = new YamlDataLoader();
    }

    @Test
    void shouldLoadCustomersFromYaml() {
        // Given
        InputStream yamlStream = getClass().getClassLoader().getResourceAsStream("customers.yml");
        assertNotNull(yamlStream, "customers.yml should be available in classpath");

        // When
        yamlDataLoader.loadData();
        List<Customer> customers = yamlDataLoader.getCustomers();

        // Then
        assertNotNull(customers);
        assertEquals(3, customers.size());

        Customer first = customers.get(0);
        assertEquals(1L, first.getId());
        assertEquals("Somnath", first.getName());
        assertEquals(30000.0, first.getCreditLimit());
    }

}
