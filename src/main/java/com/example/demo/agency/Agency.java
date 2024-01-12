package com.example.demo.agency;

import jakarta.persistence.*;


@Entity
@Table
public class Agency {
    @Id
    @SequenceGenerator(
            name = "agency_sequence",
            sequenceName = "agency_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "agency_sequence"
    )
    private Long id;
    private String name;
    private Double discount;
    private String loginUsername;
    private String password;

    public Agency(Long id, String name, Double discount, String loginUsername, String password) {
        this.id = id;
        this.name = name;
        this.discount = discount;
        this.loginUsername = loginUsername;
        this.password = password;
    }

    public Agency(String name, Double discount, String loginUsername, String password) {
        this.name = name;
        this.discount = discount;
        this.loginUsername = loginUsername;
        this.password = password;
    }

    public Agency() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getLoginUsername() {
        return loginUsername;
    }

    public void setLoginUsername(String loginUsername) {
        this.loginUsername = loginUsername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Agency{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", discount='" + discount + '\'' +
                ", loginUsername='" + loginUsername + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}



