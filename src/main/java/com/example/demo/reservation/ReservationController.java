package com.example.demo.reservation;

import com.example.demo.room.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<String> addNewReservation(@RequestBody Reservation newReservation) {
        return reservationService.addNewReservation(newReservation);
    }

    @DeleteMapping("/{id}")
    public String deleteReservation(@PathVariable Long id) {
        return reservationService.deleteReservationById(id);
    }

    // creating a new reservation with room_id, agency_id, login and password
    @PostMapping("/create")
    public ResponseEntity<String> createNewReservation(@RequestBody Map<String, Object> newReservation) {
        return reservationService.createNewReservation(newReservation);
    }

    @GetMapping("/find-rooms")
    public List<Room> findRooms(@RequestBody  Map<String, Object> lookupInfo) {
        return reservationService.roomsLookup(lookupInfo);
    }

//    // Custom endpoint for finding overlapping reservations
//    @GetMapping("/overlapping")
//    public List<Reservation> findOverlappingReservations(@RequestParam Long roomId,
//                                                         @RequestParam LocalDate startDate,
//                                                         @RequestParam LocalDate endDate) {
//        return reservationService.findOverlappingReservations(roomId, startDate, endDate);
//    }
}
