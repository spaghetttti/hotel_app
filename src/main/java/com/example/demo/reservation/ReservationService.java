package com.example.demo.reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
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

//        // Apply agency discount if applicable
//        Agency agency = newReservation.getAgency();
//        if (agency != null && !agency.getPricingPolicy().equals("0")) {
//            // Apply discount logic here
//            // For example, if agency discount is 10%, modify the price accordingly
//            double originalPrice = calculateReservationPrice(newReservation);
//            double discountedPrice = applyDiscount(originalPrice, agency.getDiscount());
//            newReservation.setPrice(discountedPrice);
//        } else {
//            // If no discount, set the price as usual
//            newReservation.setPrice(calculateReservationPrice(newReservation));
//        }

        reservationRepository.save(newReservation);
        return "New reservation has been created";
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
