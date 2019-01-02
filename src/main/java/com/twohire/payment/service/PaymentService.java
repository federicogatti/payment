package com.twohire.payment.service;

import com.twohire.payment.model.Payment;
import com.twohire.payment.model.PaymentStatus;
import com.twohire.payment.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final static Logger LOGGER = LoggerFactory.getLogger(PaymentService.class);

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment pay(final Payment payment, final long userId) {
        LOGGER.info("User {} try to pay {}", userId, payment.getTotal());
        payment.setStatus(PaymentStatus.AUTHORIZATIONSUCCESS);
        return paymentRepository.save(payment);
    }
}
