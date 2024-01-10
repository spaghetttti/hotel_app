//package com.example.demo.reservation;
//
//import com.example.demo.agency.Agency;
//import com.example.demo.agency.AgencyRepository;
//import com.example.demo.room.Room;
//import com.example.demo.room.RoomRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Configuration
//public class ReservationConfig {
//    private final RoomRepository roomRepository;
//    private final AgencyRepository agencyRepository;
//
//    @Autowired
//    public ReservationConfig(RoomRepository roomRepository, AgencyRepository agencyRepository) {
//        this.roomRepository = roomRepository;
//        this.agencyRepository = agencyRepository;
//    }
//
//
//    @Bean
//    CommandLineRunner commandLineRunnerReservationConfig(ReservationRepository reservationRepository) {
//        return args -> {
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
//        };
//    }
//}
