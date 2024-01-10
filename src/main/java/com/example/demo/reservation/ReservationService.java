package com.example.demo.reservation;
import com.example.demo.PasswordHasher;
import com.example.demo.agency.Agency;
import com.example.demo.agency.AgencyRepository;
import com.example.demo.agency.AgencyService;
import com.example.demo.room.Room;
import com.example.demo.room.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public String addNewReservation(Reservation newReservation) {
        // Check for overlapping reservations for the same room and overlapping dates
        List<Reservation> existingReservations = reservationRepository.findOverlappingReservations(
                newReservation.getRoom().getId(),
                newReservation.getStartDate(),
                newReservation.getEndDate());

        if (!existingReservations.isEmpty()) {
            throw new IllegalArgumentException("Overlapping reservation exists for the selected dates and room");
        }

        // Apply agency discount if applicable
        Agency agency = newReservation.getAgency();
        if (agency != null && !agency.getPricingPolicy().equals("0")) {
            // Apply discount logic here
            // For example, if agency discount is 10%, modify the price accordingly
//            double originalPrice = calculateReservationPrice(newReservation);
//            double discountedPrice = applyDiscount(originalPrice, agency.getDiscount());
//            newReservation.setPrice(discountedPrice);
            newReservation.getRoom().setPrice(newReservation.getRoom().getPrice() - Double.parseDouble(agency.getPricingPolicy()));
            //???? this doesn't make sense , apply actual discount logic later!!!
        } else {
            // If no discount, set the price as usual
//            newReservation.setPrice(calculateReservationPrice(newReservation));
        }

        reservationRepository.save(newReservation);
        return "New reservation has been created";
    }

    public String createNewReservation(Map<String, Object> newReservationInfo) {
        Optional<Agency> optionalAgency = agencyRepository.findById(Long.valueOf(newReservationInfo.get("agency_id").toString()));
        if (optionalAgency.isEmpty()) {
            return "Sorry no agency correspond to such id";
        }
        Agency foundAgency = optionalAgency.get();
        if (!foundAgency.getLoginUsername().equals(newReservationInfo.get("agency_login"))) {
            return "Sorry your agency login is not correct";
        }
        if (!PasswordHasher.verifyPassword(newReservationInfo.get("agency_password").toString(), foundAgency.getPassword())) {
            return "Sorry your agency password is not correct";
        }
        Optional<Room> optionalRoom = roomRepository.findById(Long.valueOf(newReservationInfo.get("room_id").toString()));
        if (optionalRoom.isEmpty()) {
            return "Sorry no room correspond to such id";
        }
        Room foundRoom = optionalRoom.get();
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

    private double calculateReservationPrice(Reservation reservation) {
        // Add your logic here to calculate the price based on the reservation details
        // For example: price per night * number of nights * number of guests
        // This is a placeholder and needs to be replaced with your actual pricing logic
        return 0.0; // Placeholder for price calculation
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
}
