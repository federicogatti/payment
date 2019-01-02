package com.twohire.payment.repository;

import com.twohire.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("select p from Trip as t left join t.payment as p where t.userId=:userId")
    List<Payment> findByUserId(Long userId);
}
