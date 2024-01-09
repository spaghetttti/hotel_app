package com.example.demo.room;

import org.springframework.beans.factory.annotation.Autowired;
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

    public String addNewRoom(Room newRoom) {
        roomRepository.save(newRoom);
        return "New room has been created";
    }

    public String deleteRoomById(Long id) {
        if (!roomRepository.existsById(id)) {
            throw new IllegalArgumentException("Room with id " + id + " doesn't exist");
        }
        roomRepository.deleteById(id);
        return "Room deleted successfully";
    }

    public String updateRoom(Long id, Room roomInfo) {
        Optional<Room> roomToUpdateOptional = roomRepository.findById(id);
        if (roomToUpdateOptional.isEmpty()) {
            throw new IllegalArgumentException("Room with id " + id + " doesn't exist");
        }

        Room existingRoom = roomToUpdateOptional.get();

        // Update room information based on roomInfo
        // You can use a similar approach as in the HotelService's updateHotel method
        // Example: Optional.ofNullable(roomInfo.getRoomNumber()).ifPresent(existingRoom::setRoomNumber);
        // Add similar lines for other room properties

        roomRepository.save(existingRoom); // Save the updated room

        return "Room updated successfully";
    }
}
