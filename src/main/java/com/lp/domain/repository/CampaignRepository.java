package com.lp.domain.repository;

import com.lp.domain.model.Campaign;
import com.lp.domain.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    Page<Campaign> findByCustomer(Customer customer, Pageable pageable);

    Optional<Campaign> findByUuid(UUID uuid);
}
