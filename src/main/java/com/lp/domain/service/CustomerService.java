package com.lp.domain.service;

import com.lp.domain.model.Customer;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface CustomerService {
    Iterable<Customer> getAllCustomers();

    Page<Customer> getPagedCustomers(
            int pageNumber,
            int pageSize,
            String sortBy,
            String sortDirection
    );

    Optional<Customer> findByUuid(UUID uuid);

    Customer saveCustomer(Customer customer);

    Customer getOwner();

    Page<Customer> getPagedCustomerReferrals(
            Customer customer,
            int pageNumber,
            int pageSize,
            String sortBy,
            String sortDirection
    );
}
