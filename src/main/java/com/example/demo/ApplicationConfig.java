package com.example.demo;

import com.example.demo.agency.Agency;
import com.example.demo.agency.AgencyRepository;
import com.example.demo.hotel.Hotel;
import com.example.demo.hotel.HotelRepository;
import com.example.demo.reservation.Reservation;
import com.example.demo.reservation.ReservationRepository;
import com.example.demo.room.Room;
import com.example.demo.room.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

@Configuration
public class ApplicationConfig {

    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final AgencyRepository agencyRepository;

    private final ReservationRepository reservationRepository;

    @Autowired
    public ApplicationConfig(
            HotelRepository hotelRepository,
            RoomRepository roomRepository,
            AgencyRepository agencyRepository,
            ReservationRepository reservationRepository
    ) {
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
        this.agencyRepository = agencyRepository;
        this.reservationRepository = reservationRepository;
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            ArrayList<Hotel> listOfHotels = new ArrayList<>();
            listOfHotels.add(new Hotel("Sample Hotel 1", "Country 1", "City 1", "Street 1", "123", 5, "hotel1@example.com", "123.456, 789.012", 5));
            listOfHotels.add(new Hotel("Sample Hotel 2", "Country 2", "City 2", "Street 2", "456", 4, "hotel2@example.com", "456.789, 012.345",3));
            listOfHotels.add(new Hotel("Sample Hotel 3", "Country 3", "City 3", "Street 3", "789", 3, "hotel3@example.com", "789.012, 345.678",4));
            listOfHotels.add(new Hotel("Sample Hotel 4", "Country 4", "City 4", "Street 4", "012", 2, "hotel4@example.com", "012.345, 678.901",6));
            hotelRepository.saveAll(listOfHotels);
            System.out.println("lol1" + hotelRepository.findAll().toString());

            ArrayList<Room> listOfRooms = new ArrayList<>();
            System.out.println("lol" + hotelRepository.findAll().toString());
            Hotel hotel1 = hotelRepository.findById(1L).orElseThrow(); // Replace 1L with the actual hotel ID
            Hotel hotel2 = hotelRepository.findById(2L).orElseThrow();
            Hotel hotel3 = hotelRepository.findById(3L).orElseThrow();
            Hotel hotel4 = hotelRepository.findById(4L).orElseThrow();

            listOfRooms.add(new Room(hotel1, "Suite", 3, 250.0, true)); // Example room for demonstration
            listOfRooms.add(new Room(hotel1, "Standard", 1, 100.0, true));
            listOfRooms.add(new Room(hotel2, "Double", 1, 150.0, true)); // Example room for demonstration
            listOfRooms.add(new Room(hotel2, "Standard", 2, 120.0, true)); // Example room for demonstration
            listOfRooms.add(new Room(hotel3, "Double", 2, 175.0, true));
            listOfRooms.add(new Room(hotel3, "Standard", 1, 80.0, true));
            listOfRooms.add(new Room(hotel4, "Suite", 4, 300.0, true));
            listOfRooms.add(new Room(hotel4, "Standard", 1, 120.0, true));


            roomRepository.saveAll(listOfRooms);

            ArrayList<Agency> listOfAgencies = new ArrayList<>();
            listOfAgencies.add(new Agency("Agency 1", "10", "agency_1_hotels", PasswordHasher.hashPassword("password1")));
            listOfAgencies.add(new Agency("Agency 2", "5", "agency_2_hotels", PasswordHasher.hashPassword("password2")));
            agencyRepository.saveAll(listOfAgencies);

            System.out.println(roomRepository.findAll());
            Room room1 = roomRepository.findById(1L).orElseThrow();
            Room room3 = roomRepository.findById(3L).orElseThrow();
            Room room6 = roomRepository.findById(6L).orElseThrow();

            Agency agency1 = agencyRepository.findById(1L).orElseThrow();
            Agency agency2 = agencyRepository.findById(2L).orElseThrow();

            Reservation reservation1 = new Reservation(room1, agency1, "John Doe", "1234 5678 9012 3456",  LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 5), 2);
            Reservation reservation2 = new Reservation(room3, agency2, "Jane Smith", "5678 9012 3456 7890",  LocalDate.of(2023, 2, 1), LocalDate.of(2023, 2, 7), 3);
            Reservation reservation3 = new Reservation(room6, agency2, "Lola Bunny", "7890 9012 1234 5678",  LocalDate.of(2023, 4, 1), LocalDate.of(2023, 7, 7), 3);

            reservationRepository.saveAll(List.of(reservation1, reservation2, reservation3));
        };
    }
//
//    @Bean
//    CommandLineRunner commandLineRunnerHotelConfig(HotelRepository hotelRepository) {
//        return args -> {
//            ArrayList<Hotel> listOfHotels = new ArrayList<>();
//            listOfHotels.add(new Hotel("Sample Hotel 1", "Country 1", "City 1", "Street 1", "123", 5, "hotel1@example.com", "123.456, 789.012", 5));
//            listOfHotels.add(new Hotel("Sample Hotel 2", "Country 2", "City 2", "Street 2", "456", 4, "hotel2@example.com", "456.789, 012.345",3));
//            listOfHotels.add(new Hotel("Sample Hotel 3", "Country 3", "City 3", "Street 3", "789", 3, "hotel3@example.com", "789.012, 345.678",4));
//            listOfHotels.add(new Hotel("Sample Hotel 4", "Country 4", "City 4", "Street 4", "012", 2, "hotel4@example.com", "012.345, 678.901",6));
//            hotelRepository.saveAll(listOfHotels);
//            System.out.println("lol1" + hotelRepository.findAll().toString());
//        };
//    }
//
//    @Bean
//    CommandLineRunner commandLineRunnerRoomConfig(RoomRepository roomRepository) {
//        return args -> {
//            ArrayList<Room> listOfRooms = new ArrayList<>();
//            System.out.println("lol" + hotelRepository.findAll().toString());
//            Hotel hotel1 = hotelRepository.findById(1L).orElseThrow(); // Replace 1L with the actual hotel ID
//            Hotel hotel2 = hotelRepository.findById(2L).orElseThrow();
//            Hotel hotel3 = hotelRepository.findById(3L).orElseThrow();
//            Hotel hotel4 = hotelRepository.findById(4L).orElseThrow();
//
//            listOfRooms.add(new Room(hotel1, "Suite", 3, 250.0, true)); // Example room for demonstration
//            listOfRooms.add(new Room(hotel1, "Standard", 1, 100.0, true));
//            listOfRooms.add(new Room(hotel2, "Double", 1, 150.0, true)); // Example room for demonstration
//            listOfRooms.add(new Room(hotel2, "Standard", 2, 120.0, true)); // Example room for demonstration
//            listOfRooms.add(new Room(hotel3, "Double", 2, 175.0, true));
//            listOfRooms.add(new Room(hotel3, "Standard", 1, 80.0, true));
//            listOfRooms.add(new Room(hotel4, "Suite", 4, 300.0, true));
//            listOfRooms.add(new Room(hotel4, "Standard", 1, 120.0, true));
//
//
//            roomRepository.saveAll(listOfRooms);
//        };
//    }
//
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
//
//
//
////    @Bean
////    CommandLineRunner commandLineRunnerReservationConfig(ReservationRepository reservationRepository) {
////        return args -> {
////            System.out.println(roomRepository.findAll());
////            Room room1 = roomRepository.findById(1L).orElseThrow();
////            Room room3 = roomRepository.findById(3L).orElseThrow();
////            Room room6 = roomRepository.findById(6L).orElseThrow();
////
////            Agency agency1 = agencyRepository.findById(1L).orElseThrow();
////            Agency agency2 = agencyRepository.findById(2L).orElseThrow();
////
////            Reservation reservation1 = new Reservation(room1, agency1, "John Doe", "1234 5678 9012 3456",  LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 5), 2);
////            Reservation reservation2 = new Reservation(room3, agency2, "Jane Smith", "5678 9012 3456 7890",  LocalDate.of(2023, 2, 1), LocalDate.of(2023, 2, 7), 3);
////            Reservation reservation3 = new Reservation(room6, agency2, "Lola Bunny", "7890 9012 1234 5678",  LocalDate.of(2023, 4, 1), LocalDate.of(2023, 7, 7), 3);
////
////            reservationRepository.saveAll(List.of(reservation1, reservation2, reservation3));
////        };
////    }
}
