package com.twohire.payment.service.pricevaluator;

import com.twohire.payment.model.Trip;

public interface PolicyPriceEvaluation {

    long MINIMUM_PRICE = 100;

    long evaluatePrice(Trip trip);
}
