package com.twohire.payment.service.pricevaluator;

import com.twohire.payment.model.Trip;

public class StandardPolicyPriceEvaluation implements PolicyPriceEvaluation {

    @Override
    public long evaluatePrice(Trip trip) {
        if (trip.getDistance() != 0) {
            return trip.getDistance();
        }
        return MINIMUM_PRICE;
    }
}
