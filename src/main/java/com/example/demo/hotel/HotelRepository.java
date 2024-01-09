package com.example.demo.hotel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    @Query("SELECT h from Hotel h WHERE h.contactInfo = ?1 OR h.gpsLocation = ?2")
    Optional<Hotel> findHotelByContactInfoOrGpsLocation(String contactInfo, String gpsLocation);
}
