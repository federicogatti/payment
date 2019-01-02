package com.twohire.payment.repository;

import com.twohire.payment.model.Trip;
import com.twohire.payment.model.dto.TripDTOCreate;
import com.twohire.payment.service.pricevaluator.PolicyPriceEvaluation;
import com.twohire.payment.service.ThresholdService;
import com.twohire.payment.util.DTO;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

public class CustomTripSaveImpl implements CustomTripSave {

    private EntityManager entityManager;

    private PolicyPriceEvaluation policyPriceEvaluation;

    private ThresholdService thresholdService;

    private PaymentRepository paymentRepository;

    public CustomTripSaveImpl(EntityManager entityManager,
                              PolicyPriceEvaluation policyPriceEvaluation,
                              ThresholdService thresholdService,
                              PaymentRepository paymentRepository) {
        this.entityManager = entityManager;
        this.policyPriceEvaluation = policyPriceEvaluation;
        this.thresholdService = thresholdService;
        this.paymentRepository = paymentRepository;
    }

    @Override
    @Transactional
    public Trip save(@DTO(TripDTOCreate.class) @RequestBody Trip trip) {
        trip.setPrice(policyPriceEvaluation.evaluatePrice(trip));
        Trip savedTrip = entityManager.merge(trip);
        thresholdService.evaluateThreshold(savedTrip);
        return savedTrip;
    }
}
