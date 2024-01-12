package com.example.demo.hotel;

import com.example.demo.agency.Agency;
import jakarta.persistence.*;

@Entity
@Table
public class Hotel {
    @Id
    @SequenceGenerator(
            name = "hotel_sequence",
            sequenceName = "hotel_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "hotel_sequence"
    )

    private Long id;
    private String name;
    private String country;
    private String city;
    private String street;
    private String number;
    private int starRating;
    private String contactInfo;
    private String gpsLocation;
    private int roomsNumber;
    @ManyToOne
    @JoinColumn(name = "agency_id")
    private Agency agency;

    public Hotel(Long id, String name, String country, String city, String street, String number, int starRating, String contactInfo, String gpsLocation, int roomsNumber, Agency agency) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.street = street;
        this.number = number;
        this.starRating = starRating;
        this.contactInfo = contactInfo;
        this.gpsLocation = gpsLocation;
        this.roomsNumber = roomsNumber;
        this.agency = agency;
    }

    public Hotel(String name, String country, String city, String street, String number, int starRating, String contactInfo, String gpsLocation, int roomsNumber, Agency agency) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.street = street;
        this.number = number;
        this.starRating = starRating;
        this.contactInfo = contactInfo;
        this.gpsLocation = gpsLocation;
        this.roomsNumber = roomsNumber;
        this.agency = agency;
    }

    public Hotel() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getStarRating() {
        return starRating;
    }

    public void setStarRating(int starRating) {
        this.starRating = starRating;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getGpsLocation() {
        return gpsLocation;
    }

    public void setGpsLocation(String gpsLocation) {
        this.gpsLocation = gpsLocation;
    }

    public int getRoomsNumber() {
        return roomsNumber;
    }

    public void setRoomsNumber(int roomsNumber) {
        this.roomsNumber = roomsNumber;
    }

    public Agency getAgency() {
        return agency;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", starRating=" + starRating +
                ", contactInfo='" + contactInfo + '\'' +
                ", gpsLocation='" + gpsLocation + '\'' +
                ", roomsNumber=" + roomsNumber +
                ", agency=" + agency +
                '}';
    }
}
