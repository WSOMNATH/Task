package com.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.customer.exception.CustomerNotFoundException;
import com.customer.exception.InvalidComparatorException;
import com.customer.model.Customer;
import com.customer.serviceImpl.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	// http://localhost:8080/customers/filter?comparator=less&limit=20000
	@GetMapping("/filter")
	public ResponseEntity<List<Customer>> getFilteredCustomers(@RequestParam String comparator,
			@RequestParam double limit) throws InvalidComparatorException, CustomerNotFoundException {

		List<Customer> customers = customerService.getCustomersByCreditLimit(comparator, limit);

		return ResponseEntity.ok(customers);
	}

	/**
	 * GET /customers/filter/more?comparator=asc&limit=3
	 * http://localhost:8080/customers/filter/more?comparator=asc&limit=20000
	 */
	@GetMapping("/filter/more")
	public ResponseEntity<List<Customer>> getSortedFilteredCustomers(@RequestParam String comparator,
			@RequestParam double limit) throws InvalidComparatorException, CustomerNotFoundException {

		List<Customer> customers = customerService.getFilteredSortedCustomers(comparator, limit);
		return ResponseEntity.ok(customers);
	}
}
