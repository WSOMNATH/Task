package com.customer.model;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class CustomerTest {

	


	    @Test
	    void testNoArgsConstructorAndSetters() {
	        Customer customer = new Customer();
	        customer.setId(1L);
	        customer.setName("Somnath");
	        customer.setCreditLimit(25000.0);

	        assertEquals(1L, customer.getId());
	        assertEquals("Somnath", customer.getName());
	        assertEquals(25000.0, customer.getCreditLimit());
	    }

	    @Test
	    void testAllArgsConstructor() {
	        Customer customer = new Customer(2L, "Somnath", 30000.0);

	        assertEquals(2L, customer.getId());
	        assertEquals("Somnath", customer.getName());
	        assertEquals(30000.0, customer.getCreditLimit());
	    }
	}


