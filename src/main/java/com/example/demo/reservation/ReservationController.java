package com.example.demo.reservation;

import com.example.demo.agency.Agency;
import com.example.demo.room.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
    public ResponseEntity<String> deleteReservation(@PathVariable Long id) {
        return reservationService.deleteReservationById(id);
    }

    // creating a new reservation with room_id, agency_id, login and password
    @PostMapping("/create")
    public ResponseEntity<String> createNewReservation(@RequestBody Map<String, Object> newReservation) {
        return reservationService.createNewReservation(newReservation);
    }

    @PostMapping("/find-rooms")
    public List<Room> findRooms(@RequestBody  Map<String, Object> lookupInfo) {
        return reservationService.roomsLookup(lookupInfo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAgency(@PathVariable Long id, @RequestBody Reservation reservationInfo) {
        return reservationService.updateReservation(id, reservationInfo);
    }
}
