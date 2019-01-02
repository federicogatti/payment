package com.twohire.payment.repository;

import com.twohire.payment.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

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
}
