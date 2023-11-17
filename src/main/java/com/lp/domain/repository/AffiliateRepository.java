package com.lp.domain.repository;

import com.lp.domain.model.Affiliate;
import com.lp.domain.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AffiliateRepository extends JpaRepository<Affiliate, Long>  {

    @Query("SELECT a.referral FROM Affiliate a WHERE a.affiliate = :affiliate")
    Page<Customer> findDistinctReferralByAffiliate(Customer affiliate, Pageable pageable);
}
