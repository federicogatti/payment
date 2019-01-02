package com.twohire.payment.repository;

import com.twohire.payment.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RepositoryRestResource
public interface TripRepository extends JpaRepository<Trip, Long>, CustomTripSave {

    /*@Override
    @RestResource(exported = false)
    <S extends Trip> S save(S s);*/

    @Override
    @RestResource(exported = false)
    void delete(Trip trip);

    @Override
    @RestResource(exported = false)
    void deleteById(Long aLong);

    List<Trip> findByUserId(@Param("userId") Long userId);

    /*@PostMapping(value = "/")
    ResponseEntity<Trip> customSave(@DTO(TripDTOCreate.class) Trip trip);*/
}
