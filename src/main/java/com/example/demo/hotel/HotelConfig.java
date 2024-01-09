package com.example.demo.hotel;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class HotelConfig {
    @Bean
    CommandLineRunner commandLineRunnerHotelConfig(HotelRepository hotelRepository) {
        return args -> {
            ArrayList<Hotel> listOfHotels = new ArrayList<>();
            listOfHotels.add(new Hotel("Sample Hotel 1", "Country 1", "City 1", "Street 1", "123", 5, "hotel1@example.com", "123.456, 789.012", 5));
            listOfHotels.add(new Hotel("Sample Hotel 2", "Country 2", "City 2", "Street 2", "456", 4, "hotel2@example.com", "456.789, 012.345",3));
            listOfHotels.add(new Hotel("Sample Hotel 3", "Country 3", "City 3", "Street 3", "789", 3, "hotel3@example.com", "789.012, 345.678",4));
            listOfHotels.add(new Hotel("Sample Hotel 4", "Country 4", "City 4", "Street 4", "012", 2, "hotel4@example.com", "012.345, 678.901",6));
            hotelRepository.saveAll(listOfHotels);
        };
    }
}
