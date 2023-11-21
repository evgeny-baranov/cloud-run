package com.lp.domain.service;

import com.lp.domain.model.Customer;
import com.lp.domain.repository.AffiliateRepository;
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

    private final AffiliateRepository affiliateRepository;

    @Getter
    private Customer owner;

    public CustomerServiceImpl(
            CustomerRepository customerRepository,
            AffiliateRepository affiliateRepository
    ) {
        this.customerRepository = customerRepository;
        this.affiliateRepository = affiliateRepository;
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
        if (customer.getAffiliate() == null && this.getOwner() != null) {
            customer.setAffiliate(this.getOwner());
        }
        return customerRepository.save(customer);
    }

    public Optional<Customer> findByUuid(UUID uuid) {
        return customerRepository.findByUuid(uuid);
    }

    public Page<Customer> getPagedCustomerReferrals(
            Customer customer,
            int pageNumber,
            int pageSize,
            String sortBy,
            String sortDirection
    ) {
        return affiliateRepository.findDistinctReferralByAffiliate(
                customer,
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

    @PostConstruct
    public void init() {
        // TODO: store in secrets, create only if doesnt exist
        Customer customer = new Customer();
        customer.setName("LP");
        this.owner = saveCustomer(customer);
    }
}
