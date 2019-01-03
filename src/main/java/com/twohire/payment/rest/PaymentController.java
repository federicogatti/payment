package com.twohire.payment.rest;

import com.twohire.payment.model.Payment;
import com.twohire.payment.repository.PaymentRepository;
import com.twohire.payment.service.PaymentService;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.data.repository.query.Param;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/payments")
@EnableBinding(ProducerChannels.class)
public class PaymentController {

    private final PaymentRepository paymentRepository;

    private final PaymentService paymentService;

    private final MessageChannel consumer;

    public PaymentController(PaymentRepository paymentRepository, PaymentService paymentService, ProducerChannels producerChannels) {
        this.paymentRepository = paymentRepository;
        this.paymentService = paymentService;
        this.consumer = producerChannels.consumer();
    }

    @GetMapping
    public List<Payment> getAll(@Param("userId") Long userId) {
        if (userId != null) {
            return paymentRepository.findByUserId(userId);
        }
        return paymentRepository.findAll();
    }

    @GetMapping(value = "/pay")
    public void payAll() {
        this.paymentService.tryToPay();
    }

    @PostMapping("/greet/{id}")
    public void publish(@PathVariable long id) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);
        optionalPayment.ifPresent(payment -> consumer.send(MessageBuilder.withPayload(payment).setHeader("content_type", "application/json").build()));
    }
}

interface ProducerChannels {

    @Output
    MessageChannel consumer();
}
