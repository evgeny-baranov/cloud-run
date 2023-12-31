package com.lp.domain.repository;

import com.lp.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUuid(UUID uuid);

    Optional<Customer> findFirstByAffiliate_Empty();
}
