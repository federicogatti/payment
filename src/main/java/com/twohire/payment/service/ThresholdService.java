package com.twohire.payment.service;

import com.twohire.payment.model.Payment;
import com.twohire.payment.model.Trip;
import com.twohire.payment.repository.GetTripsByUserId;
import com.twohire.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ThresholdService {

    private static final long THRESHOLD = 1000;

    private GetTripsByUserId getTripsByUserId;

    private PaymentRepository paymentRepository;


    @Autowired
    public ThresholdService(GetTripsByUserId getTripsByUserId,
                            PaymentRepository paymentRepository,
                            PaymentService paymentService) {
        this.getTripsByUserId = getTripsByUserId;
        this.paymentRepository = paymentRepository;
    }


    public Optional<Payment> evaluateThreshold(final Trip trip) {
        List<Trip> trips = getTripsByUserId.findByUserIdAndPaymentIsNull(trip.getUserId());
        Long threshold = this.getTotalByTrips(trips);
        if (threshold >= THRESHOLD) {
            return Optional.of(this.defineNewPayment(trips));
        }
        return Optional.empty();
    }

    private Payment defineNewPayment(final List<Trip> trips) {
        Payment payment = new Payment();
        payment.setFee(100);
        payment.setTrips(trips);
        Payment savedPayment = paymentRepository.save(payment);
        trips.forEach(t -> this.setPaymentId(t, savedPayment));
        return savedPayment;
    }

    private void setPaymentId(Trip trip, Payment payment) {
        trip.setPayment(payment);
        getTripsByUserId.save(trip);
    }

    private Long getTotalByTrips(final List<Trip> trips) {
        return trips.stream().mapToLong(Trip::getPrice).sum();
    }

}
