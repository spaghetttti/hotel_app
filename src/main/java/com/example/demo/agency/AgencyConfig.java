//package com.example.demo.agency;
//
//import com.example.demo.PasswordHasher;
//import com.example.demo.hotel.Hotel;
//import com.example.demo.hotel.HotelRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.ArrayList;
//@Configuration
//public class AgencyConfig {
//    @Bean
//    CommandLineRunner commandLineRunnerAgencyConfig(AgencyRepository agencyRepository) {
//        return args -> {
//
//            ArrayList<Agency> listOfAgencies = new ArrayList<>();
//            listOfAgencies.add(new Agency("Agency 1", "10", "agency_1_hotels", PasswordHasher.hashPassword("password1")));
//            listOfAgencies.add(new Agency("Agency 2", "5", "agency_2_hotels", PasswordHasher.hashPassword("password2")));
//            agencyRepository.saveAll(listOfAgencies);
//        };
//    }
//}
