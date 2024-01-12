package com.example.demo.reservation;
import com.example.demo.PasswordHasher;
import com.example.demo.agency.Agency;
import com.example.demo.agency.AgencyRepository;
import com.example.demo.room.Room;
import com.example.demo.room.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final AgencyRepository agencyRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, AgencyRepository agencyRepository, RoomRepository roomRepository) {
        this.reservationRepository = reservationRepository;
        this.agencyRepository = agencyRepository;
        this.roomRepository = roomRepository;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));
    }

    public ResponseEntity<String> addNewReservation(Reservation newReservation) {
        // Check for overlapping reservations for the same room and overlapping dates
        List<Reservation> existingReservations = reservationRepository.findOverlappingReservations(
                newReservation.getRoom().getId(),
                newReservation.getStartDate(),
                newReservation.getEndDate());

        if (!existingReservations.isEmpty()) {
            return new ResponseEntity<>(
                    "Overlapping reservation exists for the selected dates and room",
                    HttpStatus.BAD_REQUEST);
        }

        reservationRepository.save(newReservation);
        return new ResponseEntity<>(
                "New reservation has been created",
                HttpStatus.OK);
    }

    public ResponseEntity<String> createNewReservation(Map<String, Object> newReservationInfo) {
        Long agencyId = Long.valueOf(newReservationInfo.get("agency_id").toString());
        Optional<Agency> optionalAgency = agencyRepository.findById(agencyId);
        if (optionalAgency.isEmpty()) {
            return new ResponseEntity<>(
                    "Sorry no agency correspond to such id",
                    HttpStatus.BAD_REQUEST);
//            throw new IllegalArgumentException("Sorry no agency correspond to such id");
        }

        Agency foundAgency = optionalAgency.get();
        String agencyLogin = newReservationInfo.get("agency_login").toString();
        if (!foundAgency.getLoginUsername().equals(agencyLogin)) {
//            return "Sorry your agency login is not correct";
            return new ResponseEntity<>(
                    "Sorry your agency login is not correct",
                    HttpStatus.BAD_REQUEST);
        }
        String agencyPassword = newReservationInfo.get("agency_password").toString();
        if (!PasswordHasher.verifyPassword(agencyPassword, foundAgency.getPassword())) {
//            return "Sorry your agency password is not correct";
            return new ResponseEntity<>(
                    "Sorry your agency password is not correct",
                    HttpStatus.BAD_REQUEST);
        }
        Long roomId = Long.valueOf(newReservationInfo.get("room_id").toString());
        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        if (optionalRoom.isEmpty()) {
//            return "Sorry no room correspond to such id";
            return new ResponseEntity<>(
                    "Sorry no room correspond to such id",
                    HttpStatus.BAD_REQUEST);
        }
        Room foundRoom = optionalRoom.get();
        if (!foundRoom.getHotel().getAgency().getId().equals(agencyId)) {
//            return "Sorry this hotel doesn't collaborate with your agency";
            return new ResponseEntity<>(
                    "Sorry this hotel doesn't collaborate with your agency",
                    HttpStatus.BAD_REQUEST);
        }
        Reservation newReservation = new Reservation(
                foundRoom,
                foundAgency,
                newReservationInfo.get("name").toString(),
                newReservationInfo.get("credit_card").toString(),
                LocalDate.parse(newReservationInfo.get("start_date").toString()),
                LocalDate.parse(newReservationInfo.get("end_date").toString()),
                Integer.parseInt(newReservationInfo.get("guests_num").toString())
        );

        return addNewReservation(newReservation);
    }


    private double applyDiscount(double originalPrice, double discountPercentage) {
        // Apply the discount to the original price
        return originalPrice * (1 - (discountPercentage / 100));
    }

    public String deleteReservationById(Long id) {
        if (!reservationRepository.existsById(id)) {
            throw new IllegalArgumentException("Reservation with id " + id + " doesn't exist");
        }
        reservationRepository.deleteById(id);
        return "Reservation deleted successfully";
    }

    public List<Room> roomsLookup(Map<String, Object> lookupInfo) {
        int guests = Integer.parseInt(lookupInfo.get("guests_num").toString());
        String agencyLogin = lookupInfo.get("agency_login").toString();
        Agency foundAgency = agencyRepository.findByLoginUsername(agencyLogin).orElseThrow();

        List<Room> roomList = roomRepository.roomsLookup(guests);
        Stream<Room> roomStream = roomList.stream();

        List<Room> filteredRooms = roomStream.filter(room -> room.getHotel().getAgency().getLoginUsername().equals(agencyLogin)).toList();

        List<Room> filterRoomsByAgency = filteredRooms.stream().filter(room -> reservationRepository.findOverlappingReservations(room.getId(), LocalDate.parse(lookupInfo.get("start_date").toString()),  LocalDate.parse(lookupInfo.get("end_date").toString())).isEmpty()).toList();

        filterRoomsByAgency.forEach(room -> room.setPrice(applyDiscount(room.getPrice(), foundAgency.getDiscount())));
        return filterRoomsByAgency;
    }

}
