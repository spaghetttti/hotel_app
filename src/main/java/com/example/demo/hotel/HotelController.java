package com.example.demo.hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "api/v1/hotels")
public class HotelController {
    private final HotelService hotelService;
    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService =  hotelService;
    }

    @GetMapping
    public List<Hotel> getHotels() {
        return hotelService.getHotels();
    }

    @GetMapping("/{id}")
    public Hotel getHotelById(@PathVariable Long id) {
        return hotelService.getHotelById(id);
    }

    @PostMapping
    public String registerNewHotel(@RequestBody Hotel hotel) {
        return hotelService.addNewHotel(hotel);
    }

    @DeleteMapping(path =  "/{id}")
    public String deleteHotel(@PathVariable Long id) {
        return hotelService.deleteHotelById(id);
    }

}
