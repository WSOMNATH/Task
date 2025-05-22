//package com.customer.serviceImpl;
//
//import java.util.List;
//import java.util.stream.Collectors;
//

package com.customer.serviceImpl;

import com.customer.Iservice.ICustomer;
import com.customer.config.YamlDataLoader;
import com.customer.model.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService implements ICustomer {


	
	@Autowired
	YamlDataLoader yamlDataLoader;
	
    /**
     * Returns customers filtered by credit limit using comparator "greater" or "less".
     */
	@Override
    public List<Customer> getCustomersByCreditLimit(String comparator, double limit) {
        List<Customer> customers = yamlDataLoader.getCustomers();

        return customers.stream()
                .filter(customer -> "greater".equalsIgnoreCase(comparator)
                        ? customer.getCreditLimit() > limit
                        : customer.getCreditLimit() < limit)
                .collect(Collectors.toList());
    }
}

