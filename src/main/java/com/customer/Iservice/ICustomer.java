package com.customer.Iservice;

import java.util.List;

import com.customer.model.Customer;

public interface ICustomer {
	
List<Customer>	getCustomersByCreditLimit(String comparator, double limit);


public List<Customer> getFilteredSortedCustomers(String comparator, double limit);

}
