package com.example.demo.room;

import com.example.demo.hotel.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface RoomRepository extends JpaRepository<Room, Long> {
}
