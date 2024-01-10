package com.example.demo.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r " +
            "WHERE r.room.id = :roomId " +
            "AND (:startDate BETWEEN r.startDate AND r.endDate " +
            "OR :endDate BETWEEN r.startDate AND r.endDate " +
            "OR r.startDate BETWEEN :startDate AND :endDate)")
    List<Reservation> findOverlappingReservations(@Param("roomId") Long roomId,
                                                  @Param("startDate") LocalDate startDate,
                                                  @Param("endDate") LocalDate endDate);
}

