package com.example.demo.room;

import com.example.demo.hotel.Hotel;
import com.example.demo.hotel.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration

public class RoomConfig {
    private final HotelRepository hotelRepository;

    @Autowired
    public RoomConfig(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }
    @Bean
    CommandLineRunner commandLineRunnerRoomConfig(RoomRepository roomRepository) {
        return args -> {
            ArrayList<Room> listOfRooms = new ArrayList<>();
            Hotel hotel1 = hotelRepository.findById(1L).orElseThrow(); // Replace 1L with the actual hotel ID
            Hotel hotel2 = hotelRepository.findById(2L).orElseThrow();
            listOfRooms.add(new Room(hotel1, "Suite", 2, 200.0, true)); // Example room for demonstration
            listOfRooms.add(new Room(hotel1, "Standard", 1, 100.0, true));
            listOfRooms.add(new Room(hotel2, "Double", 1, 150.0, true)); // Example room for demonstration
            listOfRooms.add(new Room(hotel2, "Standard", 1, 100.0, true));

            roomRepository.saveAll(listOfRooms);
        };
    }

}
