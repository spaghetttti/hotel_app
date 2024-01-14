package com.example.demo.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(Long id) {
        return roomRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Room not found"));
    }

    public ResponseEntity<String> addNewRoom(Room newRoom) {
        roomRepository.save(newRoom);
        return new ResponseEntity<>(
                "New room has been created",
                HttpStatus.OK);
    }

    public ResponseEntity<String> deleteRoomById(Long id) {
        if (!roomRepository.existsById(id)) {
            return new ResponseEntity<>(
                    "Room with id " + id + " doesn't exist",
                    HttpStatus.BAD_REQUEST);
        }
        roomRepository.deleteById(id);
        return new ResponseEntity<>(
                "Room deleted successfully",
                HttpStatus.OK);
    }

    public ResponseEntity<String> updateRoom(Long id, Room roomInfo) {
        Optional<Room> roomToUpdateOptional = roomRepository.findById(id);
        if (roomToUpdateOptional.isEmpty()) {
            return new ResponseEntity<>(
                    "Room with id " + id + " doesn't exist",
                    HttpStatus.BAD_REQUEST);
        }

        Room existingRoom = roomToUpdateOptional.get();

        // Update room information based on roomInfo
        Optional.ofNullable(roomInfo.getRoomType()).ifPresent(existingRoom::setRoomType);
        Optional.ofNullable(roomInfo.getPrice()).filter(price -> price > 0 && price <= 100000).ifPresent(existingRoom::setPrice);
        Optional.of(roomInfo.getNumBeds()).filter(beds -> beds > 0 && beds <= 100).ifPresent(existingRoom::setNumBeds);


        roomRepository.save(existingRoom); // Save the updated room
        return new ResponseEntity<>(
                "Room updated successfully",
                HttpStatus.OK);
    }
}
