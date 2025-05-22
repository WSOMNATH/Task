package com.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.customer.model.Customer;
import com.customer.serviceImpl.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	//private  CustomerService customerService;

//	public CustomerController(CustomerService customerService) {
//		this.customerService = customerService;
//	}

	/**
	 * GET /customers/filter?comparator=greater&limit=20000
	 */
	@GetMapping("/filter")
	public List<Customer> getFilteredCustomers(@RequestParam String comparator, @RequestParam double limit) {
		return customerService.getCustomersByCreditLimit(comparator, limit);
	}
}
