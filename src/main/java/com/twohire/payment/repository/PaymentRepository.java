package com.twohire.payment.repository;

import com.twohire.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
