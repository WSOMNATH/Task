package com.customer.services;


	
	


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
	import org.mockito.InjectMocks;
	import org.mockito.Mock;
	import org.mockito.MockitoAnnotations;

import com.customer.config.YamlDataLoader;
import com.customer.exception.CustomerNotFoundException;
import com.customer.exception.InvalidComparatorException;
import com.customer.model.Customer;

import com.customer.serviceImpl.CustomerService;

	import java.util.List;

	import static org.junit.jupiter.api.Assertions.*;
	import static org.mockito.Mockito.*;

	class CustomerServiceTest {

		
		@Mock
		YamlDataLoader yamlDataLoader;
		

	    @InjectMocks
	    private CustomerService customerService;

	  
	   
	        private List<Customer> sampleCustomers;

	        @BeforeEach
	        void setUp() {
	            MockitoAnnotations.openMocks(this);

	            sampleCustomers = List.of(
	                    new Customer(1L, "Alice", 30000d),
	                    new Customer(2L, "Bob", 15000d),
	                    new Customer(3L, "Charlie", 5000d)
	            );
	        }

	        // Test getCustomersByCreditLimit with "greater" comparator - success
	        @Test
	        void testGetCustomersByCreditLimit_greater_Success() throws Exception {
	            when(yamlDataLoader.getCustomers()).thenReturn(sampleCustomers);

	            List<Customer> result = customerService.getCustomersByCreditLimit("greater", 20000);

	            assertEquals(1, result.size());
	            assertTrue(result.stream().allMatch(c -> c.getCreditLimit() > 20000));
	        }

	        // Test getCustomersByCreditLimit with "less" comparator - success
	        @Test
	        void testGetCustomersByCreditLimit_less_Success() throws Exception {
	            when(yamlDataLoader.getCustomers()).thenReturn(sampleCustomers);

	            List<Customer> result = customerService.getCustomersByCreditLimit("less", 20000);

	            assertEquals(2, result.size());
	            assertTrue(result.stream().allMatch(c -> c.getCreditLimit() < 20000));
	        }

	        // Test getCustomersByCreditLimit with invalid comparator - exception
	        @Test
	        void testGetCustomersByCreditLimit_InvalidComparatorException() {
	            when(yamlDataLoader.getCustomers()).thenReturn(sampleCustomers);

	            InvalidComparatorException ex = assertThrows(InvalidComparatorException.class, () -> {
	                customerService.getCustomersByCreditLimit("invalid", 20000);
	            });

	            assertTrue(ex.getMessage().contains("Comparator must be 'greater' or 'less'"));
	        }

	        // Test getCustomersByCreditLimit when no customers found - exception
	        @Test
	        void testGetCustomersByCreditLimit_NoCustomersFoundException() {
	            when(yamlDataLoader.getCustomers()).thenReturn(sampleCustomers);

	            CustomerNotFoundException ex = assertThrows(CustomerNotFoundException.class, () -> {
	                customerService.getCustomersByCreditLimit("greater", 50000);
	            });

	            assertTrue(ex.getMessage().contains("No customers found"));
	        }

	        // Test getFilteredSortedCustomers with "asc" comparator - success
	        @Test
	        void testGetFilteredSortedCustomers_asc_Success() throws Exception {
	            when(yamlDataLoader.getCustomers()).thenReturn(sampleCustomers);

	            List<Customer> result = customerService.getFilteredSortedCustomers("asc", 10000);

	            assertFalse(result.isEmpty());
	            assertTrue(result.get(0).getCreditLimit() <= result.get(1).getCreditLimit());
	            assertTrue(result.stream().allMatch(c -> c.getCreditLimit() > 10000));
	        }

	        // Test getFilteredSortedCustomers with "desc" comparator - success
	        @Test
	        void testGetFilteredSortedCustomers_desc_Success() throws Exception {
	            when(yamlDataLoader.getCustomers()).thenReturn(sampleCustomers);

	            List<Customer> result = customerService.getFilteredSortedCustomers("desc", 10000);

	            assertFalse(result.isEmpty());
	            assertTrue(result.get(0).getCreditLimit() >= result.get(1).getCreditLimit());
	            assertTrue(result.stream().allMatch(c -> c.getCreditLimit() > 10000));
	        }

	        // Test getFilteredSortedCustomers with invalid comparator - exception
	        @Test
	        void testGetFilteredSortedCustomers_InvalidComparatorException() {
	            when(yamlDataLoader.getCustomers()).thenReturn(sampleCustomers);

	            InvalidComparatorException ex = assertThrows(InvalidComparatorException.class, () -> {
	                customerService.getFilteredSortedCustomers("invalid", 10000);
	            });

	            assertTrue(ex.getMessage().contains("Comparator must be 'asc' or 'desc'"));
	        }

	        // Test getFilteredSortedCustomers when no customers found - exception
	        @Test
	        void testGetFilteredSortedCustomers_NoCustomersFoundException() {
	            when(yamlDataLoader.getCustomers()).thenReturn(sampleCustomers);

	            CustomerNotFoundException ex = assertThrows(CustomerNotFoundException.class, () -> {
	                customerService.getFilteredSortedCustomers("asc", 50000);
	            });

	            assertTrue(ex.getMessage().contains("No customers found"));
	        }
	    }

	    
	    

	    
	    
	


