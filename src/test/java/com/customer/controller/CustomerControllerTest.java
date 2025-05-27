package com.customer.controller;


	import com.customer.exception.CustomerNotFoundException;
	import com.customer.exception.InvalidComparatorException;
	import com.customer.model.Customer;
	import com.customer.serviceImpl.CustomerService;

	import org.junit.jupiter.api.BeforeEach;
	import org.junit.jupiter.api.Test;
	import org.mockito.*;

	import org.springframework.http.ResponseEntity;

	import java.util.List;

	import static org.junit.jupiter.api.Assertions.*;
	import static org.mockito.Mockito.*;

	class CustomerControllerTest {

	    @Mock
	    private CustomerService customerService;

	    @InjectMocks
	    private CustomerController customerController;

	    private final List<Customer> sampleCustomers = List.of(
	            new Customer(1L, "Somnath", 30000d),
	            new Customer(2L, "yash", 40000d)
	    );

	    @BeforeEach
	    void setup() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    void testGetFilteredCustomers_Success() throws Exception {
	        when(customerService.getCustomersByCreditLimit("greater", 20000)).thenReturn(sampleCustomers);

	        ResponseEntity<List<Customer>> response = customerController.getFilteredCustomers("greater", 20000);

	        assertEquals(200, response.getStatusCodeValue());
	        assertEquals(sampleCustomers, response.getBody());

	        verify(customerService).getCustomersByCreditLimit("greater", 20000);
	    }

	    @Test
	    void testGetFilteredCustomers_Exception() throws Exception {
	        when(customerService.getCustomersByCreditLimit("invalid", 20000))
	                .thenThrow(new InvalidComparatorException("Invalid comparator"));

	        InvalidComparatorException ex = assertThrows(InvalidComparatorException.class, () -> 
	            customerController.getFilteredCustomers("invalid", 20000)
	        );

	        assertEquals("Invalid comparator", ex.getMessage());
	        verify(customerService).getCustomersByCreditLimit("invalid", 20000);
	    }

	    @Test
	    void testGetSortedFilteredCustomers_Success() throws Exception {
	        when(customerService.getFilteredSortedCustomers("asc", 20000)).thenReturn(sampleCustomers);

	        ResponseEntity<List<Customer>> response = customerController.getSortedFilteredCustomers("asc", 20000);

	        assertEquals(200, response.getStatusCodeValue());
	        assertEquals(sampleCustomers, response.getBody());

	        verify(customerService).getFilteredSortedCustomers("asc", 20000);
	    }

	    @Test
	    void testGetSortedFilteredCustomers_Exception() throws Exception {
	        when(customerService.getFilteredSortedCustomers("wrong", 20000))
	                .thenThrow(new CustomerNotFoundException("No customers found"));

	        CustomerNotFoundException ex = assertThrows(CustomerNotFoundException.class, () ->
	                customerController.getSortedFilteredCustomers("wrong", 20000)
	        );

	        assertEquals("No customers found", ex.getMessage());
	        verify(customerService).getFilteredSortedCustomers("wrong", 20000);
	    }
	}


