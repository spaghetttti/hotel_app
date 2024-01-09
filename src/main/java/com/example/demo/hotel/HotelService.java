package com.example.demo.hotel;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
        return new Hotel(id, "Sample Hotel", "Sample Country", "Sample City", "Sample Street",
                "123", 5, "testhotel@example.com", "123.456, 789.012", 4);
    }


    public String addNewHotel(Hotel newHotel) {
        Optional<Hotel> hotel = hotelRepository.findHotelByContactInfoOrGpsLocation(newHotel.getContactInfo(), newHotel.getGpsLocation());
        if (hotel.isPresent()) {
            throw new IllegalArgumentException("sorry mate, this hotel already exists");
        }
        hotelRepository.save(newHotel);
        return "new hotel has been created";
    }

    public String deleteHotelById(Long id) {
        Optional<Hotel> hotelToUpdate = hotelRepository.findById(id);
        if (hotelToUpdate.isEmpty()) {
            throw new IllegalArgumentException("sorry mate, hotel with id of " + id + " doesn't exist");
        }
        hotelRepository.deleteById(id);
        return "Hotel deleted successfully";
    }

    @Transactional
    public String updateHotel(Long id, Hotel hotelInfo) {
        Optional<Hotel> hotelToUpdateOptional = hotelRepository.findById(id);
        if (hotelToUpdateOptional.isEmpty()) {
            throw new IllegalArgumentException("Sorry, hotel with id of " + id + " doesn't exist");
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
        Optional.of(hotelInfo.getRoomsNumber()).ifPresent(existingHotel::setRoomsNumber);

        hotelRepository.save(existingHotel); // Save the updated hotel

        return "Hotel updated successfully";
    }
}
