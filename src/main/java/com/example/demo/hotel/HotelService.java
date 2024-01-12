package com.example.demo.hotel;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<Hotel> getHotels() {
        return hotelRepository.findAll();
    }

    public Hotel getHotelById(@PathVariable Long id) {
        return hotelRepository.findById(id).orElseThrow();
    }


    public ResponseEntity<String> addNewHotel(Hotel newHotel) {
        Optional<Hotel> hotel = hotelRepository.findHotelByContactInfoOrGpsLocation(newHotel.getContactInfo(), newHotel.getGpsLocation());
        if (hotel.isPresent()) {
            return new ResponseEntity<>(
                    "sorry mate, this hotel already exists",
                    HttpStatus.BAD_REQUEST);
        }
        hotelRepository.save(newHotel);
        return new ResponseEntity<>(
                "new hotel has been created",
                HttpStatus.OK);
    }

    public ResponseEntity<String> deleteHotelById(Long id) {
        Optional<Hotel> hotelToUpdate = hotelRepository.findById(id);
        if (hotelToUpdate.isEmpty()) {
            return new ResponseEntity<>(
                    "Sorry, hotel with id of " + id + " doesn't exist",
                    HttpStatus.BAD_REQUEST);
        }
        hotelRepository.deleteById(id);
        return new ResponseEntity<>(
                "Hotel deleted successfully",
                HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> updateHotel(Long id, Hotel hotelInfo) {
        Optional<Hotel> hotelToUpdateOptional = hotelRepository.findById(id);
        if (hotelToUpdateOptional.isEmpty()) {
            return new ResponseEntity<>(
                    "Sorry, hotel with id of " + id + " doesn't exist",
                    HttpStatus.BAD_REQUEST);
        }

        Hotel existingHotel = hotelToUpdateOptional.get();

        Optional.ofNullable(hotelInfo.getName()).ifPresent(existingHotel::setName);
        Optional.ofNullable(hotelInfo.getCountry()).ifPresent(existingHotel::setCountry);
        Optional.ofNullable(hotelInfo.getCity()).ifPresent(existingHotel::setCity);
        Optional.ofNullable(hotelInfo.getStreet()).ifPresent(existingHotel::setStreet);
        Optional.ofNullable(hotelInfo.getNumber()).ifPresent(existingHotel::setNumber);
        Optional.ofNullable(hotelInfo.getStarRating())
                .filter(starRating -> starRating > 0 && starRating <= 5)
                .ifPresent(existingHotel::setStarRating);
        Optional.ofNullable(hotelInfo.getContactInfo()).ifPresent(existingHotel::setContactInfo);
        Optional.ofNullable(hotelInfo.getGpsLocation()).ifPresent(existingHotel::setGpsLocation);
        Optional.ofNullable(hotelInfo.getRoomsNumber()).filter(numberOfRooms -> numberOfRooms > 0 && numberOfRooms <= 10000).ifPresent(existingHotel::setRoomsNumber);

        hotelRepository.save(existingHotel); // Save the updated hotel

        return new ResponseEntity<>(
                "Hotel updated successfully",
                HttpStatus.OK);

    }
}
