package com.lp.domain.service;

import com.lp.domain.model.Customer;
import com.lp.domain.repository.CustomerRepository;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Getter
    private Customer owner;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Page<Customer> getPagedCustomers(
            int pageNumber,
            int pageSize,
            String sortBy,
            String sortDirection
    ) {
        return customerRepository.findAll(
                PageRequest.of(
                        pageNumber - 1,
                        pageSize,
                        Sort.by(
                                Sort.Direction.fromString(sortDirection),
                                sortBy
                        )
                )
        );
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<Customer> findByUuid(UUID uuid) {
        return customerRepository.findByUuid(uuid);
    }


    @PostConstruct
    public void init() {
        // TODO: store in secrets, create only if doesnt exist
        Customer customer = new Customer();
        customer.setName("LP");
        this.owner = saveCustomer(customer);
    }
}
