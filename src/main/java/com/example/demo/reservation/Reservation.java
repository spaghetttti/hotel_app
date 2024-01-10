package com.example.demo.reservation;

import com.example.demo.agency.Agency;
import com.example.demo.room.Room;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table

public class Reservation {
    @Id
    @SequenceGenerator(
            name = "reservation_sequence",
            sequenceName = "reservation_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "reservation_sequence"
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "agency_id")
    private Agency agency;

    private String guestName;
    private String creditCardInfo;
    private LocalDate startDate;
    private LocalDate endDate;
    private int numberOfGuests;

    public Reservation(Long id, Room room, Agency agency, String guestName, String creditCardInfo, LocalDate startDate, LocalDate endDate, int numberOfGuests) {
        this.id = id;
        this.room = room;
        this.agency = agency;
        this.guestName = guestName;
        this.creditCardInfo = creditCardInfo;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfGuests = numberOfGuests;
    }

    public Reservation(Room room, Agency agency, String guestName, String creditCardInfo, LocalDate startDate, LocalDate endDate, int numberOfGuests) {
        this.room = room;
        this.agency = agency;
        this.guestName = guestName;
        this.creditCardInfo = creditCardInfo;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfGuests = numberOfGuests;
    }

    public Reservation() {

    }

    public Long getId() {
        return id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getCreditCardInfo() {
        return creditCardInfo;
    }

    public void setCreditCardInfo(String creditCardInfo) {
        this.creditCardInfo = creditCardInfo;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", room=" + room +
                ", agency=" + agency +
                ", guestName='" + guestName + '\'' +
                ", creditCardInfo='" + creditCardInfo + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", numberOfGuests=" + numberOfGuests +
                '}';
    }
}
