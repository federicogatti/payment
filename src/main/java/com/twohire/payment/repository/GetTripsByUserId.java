package com.twohire.payment.repository;

import com.twohire.payment.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface GetTripsByUserId extends JpaRepository<Trip, Long> {

    @Query("select t from Trip AS t where t.userId=:userId and t.payment is null ")
    List<Trip> findByUserId(@Param("userId") Long userId);

}
