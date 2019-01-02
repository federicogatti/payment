package com.twohire.payment.repository;

import com.twohire.payment.model.Payment;
import com.twohire.payment.model.Trip;
import com.twohire.payment.model.dto.TripDTOCreate;
import com.twohire.payment.service.PaymentService;
import com.twohire.payment.service.pricevaluator.PolicyPriceEvaluation;
import com.twohire.payment.service.ThresholdService;
import com.twohire.payment.util.DTO;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

public class CustomTripSaveImpl implements CustomTripSave {

    private EntityManager entityManager;

    private PolicyPriceEvaluation policyPriceEvaluation;

    private ThresholdService thresholdService;

    private PaymentService paymentService;

    public CustomTripSaveImpl(EntityManager entityManager,
                              PolicyPriceEvaluation policyPriceEvaluation,
                              ThresholdService thresholdService,
                              PaymentService paymentService) {
        this.entityManager = entityManager;
        this.policyPriceEvaluation = policyPriceEvaluation;
        this.thresholdService = thresholdService;
        this.paymentService = paymentService;
    }

    @Override
    @Transactional
    public Trip save(Trip trip) {
        trip.setPrice(policyPriceEvaluation.evaluatePrice(trip));
        Trip savedTrip = entityManager.merge(trip);
        Optional<Payment> optionalPayment = thresholdService.evaluateThreshold(savedTrip);
        optionalPayment.ifPresent(payment -> paymentService.pay(payment, trip.getUserId()));
        return savedTrip;
    }
}
