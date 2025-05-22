package com.customer.services;


	
	
	import org.junit.jupiter.api.BeforeEach;

// src/test/java/com/example/customer/service/CustomerServiceTest.java
	

	import org.junit.jupiter.api.Test;
	import org.mockito.InjectMocks;
	import org.mockito.Mock;
	import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.customer.config.YamlDataLoader;
import com.customer.model.Customer;

import com.customer.serviceImpl.CustomerService;

import java.util.Arrays;
	import java.util.List;

	import static org.junit.jupiter.api.Assertions.*;
	import static org.mockito.Mockito.*;

	class CustomerServiceTest {

		
		@Mock
		YamlDataLoader yamlDataLoader;
		
//	    @Mock
//	    private CustomerRepository customerRepository;

	    @InjectMocks
	    private CustomerService customerService;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

//	    @Test
//	    void testGetCustomersByCreditLimitGreaterThan() {
//	        List<Customer> mockCustomers = Arrays.asList(
//	                new Customer(1L, "Alice", 30000.0),
//	                new Customer(2L, "Bob", 15000.0)
//	        );
//
//	        when(yamlDataLoader.getCustomers()).thenReturn(mockCustomers);
//
//	        List<Customer> result = customerService.getCustomersByCreditLimit("greater", 20000.0);
//
//	        assertEquals(1, result.size());
//	        assertEquals("Alice", result.get(0).getName());
//	    }
//
//	    @Test
//	    void testGetCustomersByCreditLimitLessThan() {
//	        List<Customer> mockCustomers = Arrays.asList(
//	                new Customer(1L, "Alice", 30000.0),
//	                new Customer(2L, "Bob", 15000.0)
//	        );
//
//	        when(customerRepository.findAll()).thenReturn(mockCustomers);
//
//	        List<Customer> result = customerService.getCustomersByCreditLimit("less", 20000.0);
//
//	        assertEquals(1, result.size());
//	        assertEquals("Bob", result.get(0).getName());
//	    }
	    
	    
	    
	    

//
//	        @Mock
//	        private YamlDataLoader yamlDataLoader;
//
//	        @InjectMocks
//	        private CustomerService customerService;
//
//	        @BeforeEach
//	        void setUp() {
//	            MockitoAnnotations.openMocks(this);
//	        }

//	        @Test
//	        void testGetCustomersByCreditLimitGreaterThan() {
//	            List<Customer> mockCustomers = Arrays.asList(
//	                    new Customer(1L, "Somnath", 30000.0),
//	                    new Customer(2L, "Yash", 15000.0)
//	            );
//
//	            when(yamlDataLoader.getCustomers()).thenReturn(mockCustomers);
//
//	            List<Customer> result = customerService.getCustomersByCreditLimit("greater", 20000.0);
//
//	            assertEquals(1, result.size());
//	            assertEquals("Alice", result.get(0).getName());
//	        }
//
//	        @Test
//	        void testGetCustomersByCreditLimitLessThan() {
//	            List<Customer> mockCustomers = Arrays.asList(
//	                    new Customer(1L, "Alice", 30000.0),
//	                    new Customer(2L, "Bob", 15000.0)
//	            );
//
//	            when(yamlDataLoader.getCustomers()).thenReturn(mockCustomers);
//
//	            List<Customer> result = customerService.getCustomersByCreditLimit("less", 20000.0);
//
//	            assertEquals(1, result.size());
//	            assertEquals("Bob", result.get(0).getName());
//	        }
	    
	    
	    
//	    
//	    @Test
//	    void testGetCustomersFromYamlGreaterThan() {
//	        List<Customer> mockCustomers = Arrays.asList(
//	                new Customer(1L, "Somnath", 30000.0),
//	                new Customer(2L, "Yash",  15000.0),
//	                new Customer(3L, "Vishal", 25000.0)
//	        );
//
//	        when(yamlDataLoader.getCustomers()).thenReturn(mockCustomers);
//
//	        List<Customer> result = customerService.getCustomersByCreditLimit("greater", 20000.0);
//
//	        assertEquals(2, result.size());
//	        assertTrue(result.stream().anyMatch(c -> c.getName().equals("Somnath")));
//	        assertTrue(result.stream().anyMatch(c -> c.getName().equals("Vishal")));
//	    }

	    
	    
	    @Test
	    void testGreaterThanReturnsCorrectCustomers() {
	    	
	        List<Customer> mockCustomers = Arrays.asList(
	                new Customer(1L, "Somnath", 30000.0),
	                new Customer(2L, "Yash",  15000.0),
	                new Customer(3L, "Vishal", 25000.0)
	        );

	        when(yamlDataLoader.getCustomers()).thenReturn(mockCustomers);

	      //  List<Customer> result = customerService.getCustomersByCreditLimit("greater", 20000.0);
	    	
	    	
	        List<Customer> result = customerService.getCustomersByCreditLimit("greater", 20000.0);

	        assertEquals(2, result.size());
	        assertTrue(result.stream().anyMatch(c -> c.getName().equals("Somnath")));
	        assertTrue(result.stream().anyMatch(c -> c.getName().equals("Vishal")));
	    }

	    @Test
	    void testLessThanReturnsCorrectCustomers() {
	    	
	    	
	        List<Customer> mockCustomers = Arrays.asList(
	                new Customer(1L, "Somnath", 30000.0),
	                new Customer(2L, "Yash",  15000.0),
	                new Customer(3L, "Vishal", 25000.0)
	        );

	        when(yamlDataLoader.getCustomers()).thenReturn(mockCustomers);

	       // List<Customer> result = customerService.getCustomersByCreditLimit("greater", 20000.0);
	        List<Customer> result = customerService.getCustomersByCreditLimit("less", 20000.0);

	        assertEquals(1, result.size());
	        assertEquals("Yash", result.get(0).getName());
	    }

	    @Test
	    void testNoCustomersMatchCondition() {
	        List<Customer> mockCustomers = Arrays.asList(
	                new Customer(1L, "Somnath", 30000.0),
	                new Customer(2L, "Yash",  15000.0),
	                new Customer(3L, "Vishal", 25000.0)
	        );

	        when(yamlDataLoader.getCustomers()).thenReturn(mockCustomers);

	        //List<Customer> result = customerService.getCustomersByCreditLimit("greater", 20000.0);
	    	
	    	
	        List<Customer> result = customerService.getCustomersByCreditLimit("greater", 50000.0);

	        assertTrue(result.isEmpty());
	    }

	    @Test
	    void testInvalidComparatorReturnsAllLess() {
	        // In your method, any non-"greater" input behaves as "less"
	    	
	    	
	        List<Customer> mockCustomers = Arrays.asList(
	                new Customer(1L, "Somnath", 30000.0),
	                new Customer(2L, "Yash",  15000.0),
	                new Customer(3L, "Vishal", 25000.0)
	        );

	        when(yamlDataLoader.getCustomers()).thenReturn(mockCustomers);

	      //  List<Customer> result = customerService.getCustomersByCreditLimit("greater", 20000.0);
	    	
	        List<Customer> result = customerService.getCustomersByCreditLimit("invalid", 20000.0);

	        assertEquals(1, result.size());
	        assertEquals("Yash", result.get(0).getName());
	    }
	    
	    }

	    
	    
	


