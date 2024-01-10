package com.example.demo.room;

import com.example.demo.hotel.Hotel;
import jakarta.persistence.*;

@Entity
@Table
public class Room {
    @Id
    @SequenceGenerator(
            name = "room_sequence",
            sequenceName = "room_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "room_sequence"
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    private String roomType;

    private int numBeds;

    private double price;

    public Room(Long id, Hotel hotel, String roomType, int numBeds, double price) {
        this.id = id;
        this.hotel = hotel;
        this.roomType = roomType;
        this.numBeds = numBeds;
        this.price = price;
    }

    public Room(Hotel hotel, String roomType, int numBeds, double price) {
        this.hotel = hotel;
        this.roomType = roomType;
        this.numBeds = numBeds;
        this.price = price;
    }

    public Room() {

    }

    public Long getId() {
        return id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getNumBeds() {
        return numBeds;
    }

    public void setNumBeds(int numBeds) {
        this.numBeds = numBeds;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }



    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", hotel=" + hotel +
                ", roomType='" + roomType + '\'' +
                ", numBeds=" + numBeds +
                ", price=" + price +
                '}';
    }
}
