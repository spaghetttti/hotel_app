package com.example.demo.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/rooms")
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> getRooms() {
        return roomService.getRooms();
    }

    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable Long id) {
        return roomService.getRoomById(id);
    }

    @PostMapping
    public ResponseEntity<String> addNewRoom(@RequestBody Room room) {
        return roomService.addNewRoom(room);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable Long id) {
        return roomService.deleteRoomById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateRoom(@PathVariable Long id, @RequestBody Room roomInfo) {
        return roomService.updateRoom(id, roomInfo);
    }
}
