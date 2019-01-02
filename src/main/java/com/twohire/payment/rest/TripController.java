package com.twohire.payment.rest;

import com.twohire.payment.model.Trip;
import com.twohire.payment.model.dto.TripDTOCreate;
import com.twohire.payment.repository.TripRepository;
import com.twohire.payment.util.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/api/v1/trips")
public class TripController {

    private final TripRepository tripRepository;

    @Autowired
    public TripController(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }


    @PostMapping
    public Trip save(@DTO(TripDTOCreate.class) @RequestBody Trip trip) {
        return tripRepository.save(trip);
    }

}
