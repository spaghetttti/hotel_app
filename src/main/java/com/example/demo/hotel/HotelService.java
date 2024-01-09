package com.example.demo.hotel;

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
        boolean hotelExistsById = hotelRepository.existsById(id);
        if (!hotelExistsById) {
            throw new IllegalArgumentException("sorry mate, hotel with id of " + id + " doesn't exist");
        }
        hotelRepository.deleteById(id);
        return "Hotel deleted successfully";
    }
}
