package com.lp.domain.service;

import com.lp.domain.model.Customer;
import com.lp.domain.repository.CustomerRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Iterable<Customer> getAllCustomers(int page, int size, String sortBy, String direction) {
        return null;
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @PostConstruct
    public void init() {
        Customer customer = new Customer();
        customer.setName("LP");

        customerRepository.save(customer);
    }
}
