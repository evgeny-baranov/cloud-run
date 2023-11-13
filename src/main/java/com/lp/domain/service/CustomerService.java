package com.lp.domain.service;

import com.lp.domain.model.Customer;

public interface CustomerService {
    Iterable<Customer> getAllCustomers(
            int page,
            int size,
            String sortBy,
            String direction
    );

    Customer saveCustomer(Customer customer);
}
