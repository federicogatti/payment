package com.twohire.payment.rest;

import com.twohire.payment.model.Payment;
import com.twohire.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "/api/v1/payments")
public class PaymentController {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentController(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @GetMapping
    public List<Payment> getAll(@Param("userId") Long userId) {
        if (userId != null) {
            return paymentRepository.findByUserId(userId);
        }
        return paymentRepository.findAll();
    }
}
