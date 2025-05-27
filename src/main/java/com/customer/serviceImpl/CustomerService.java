
package com.customer.serviceImpl;

import com.customer.Iservice.ICustomer;
import com.customer.config.YamlDataLoader;
import com.customer.exception.CustomerNotFoundException;
import com.customer.exception.InvalidComparatorException;
import com.customer.model.Customer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService implements ICustomer {


	
	@Autowired
	YamlDataLoader yamlDataLoader;
	

	
     
	    @Override
	    public List<Customer> getCustomersByCreditLimit(String comparator, double limit)
	            throws InvalidComparatorException, CustomerNotFoundException {

	        List<Customer> customers = yamlDataLoader.getCustomers();

	        List<Customer> filtered;

	        if ("greater".equalsIgnoreCase(comparator)) {
	            filtered = customers.stream()
	                    .filter(c -> c.getCreditLimit() > limit)
	                    .collect(Collectors.toList());
	        } else if ("less".equalsIgnoreCase(comparator)) {
	            filtered = customers.stream()
	                    .filter(c -> c.getCreditLimit() < limit)
	                    .collect(Collectors.toList());
	        } else {
	            throw new InvalidComparatorException("Comparator must be 'greater' or 'less'. Provided: " + comparator);
	        }

	        if (filtered.isEmpty()) {
	            throw new CustomerNotFoundException("No customers found with credit limit " + comparator + " than " + limit);
	        }

	        return filtered;
	    }

	    
	    
	   
	   
	    
	    
	    /**
	     * Filters customers with creditLimit > limit and sorts by asc or desc.
	     */
	    @Override
	    public List<Customer> getFilteredSortedCustomers(String comparator, double limit)
	            throws InvalidComparatorException, CustomerNotFoundException {

	        Comparator<Customer> sortOrder;

	        if ("asc".equalsIgnoreCase(comparator)) {
	            sortOrder = Comparator.comparing(Customer::getCreditLimit);
	        } else if ("desc".equalsIgnoreCase(comparator)) {
	            sortOrder = Comparator.comparing(Customer::getCreditLimit).reversed();
	        } else {
	            throw new InvalidComparatorException("Comparator must be 'asc' or 'desc'. Provided: " + comparator);
	        }

	        List<Customer> customers = yamlDataLoader.getCustomers().stream()
	                .filter(c -> c.getCreditLimit() > limit)
	                .sorted(sortOrder)
	                .collect(Collectors.toList());

	        if (customers.isEmpty()) {
	            throw new CustomerNotFoundException("No customers found with credit limit > " + limit);
	        }

	        return customers;
	    }
	}

	


