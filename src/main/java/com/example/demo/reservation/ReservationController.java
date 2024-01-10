package com.example.demo.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public Reservation getReservationById(@PathVariable Long id) {
        return reservationService.getReservationById(id);
    }

    @PostMapping
    public String addNewReservation(@RequestBody Reservation newReservation) {
        return reservationService.addNewReservation(newReservation);
    }

    @DeleteMapping("/{id}")
    public String deleteReservation(@PathVariable Long id) {
        return reservationService.deleteReservationById(id);
    }

//    // Custom endpoint for finding overlapping reservations
//    @GetMapping("/overlapping")
//    public List<Reservation> findOverlappingReservations(@RequestParam Long roomId,
//                                                         @RequestParam LocalDate startDate,
//                                                         @RequestParam LocalDate endDate) {
//        return reservationService.findOverlappingReservations(roomId, startDate, endDate);
//    }
}
