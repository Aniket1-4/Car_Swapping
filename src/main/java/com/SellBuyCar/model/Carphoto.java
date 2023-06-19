package com.SellBuyCar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "carphoto")
public class Carphoto {
    @Id
    @Column(name = "car_photo_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    @Column(name = "car_photo", length = 250)
    private String carPhoto;

    @Column(name = "car_photo4", length = 250)
    private String carPhoto4;

    @Column(name = "car_photo5", length = 250)
    private String carPhoto5;

    @Column(name = "car_photo6", length = 250)
    private String carPhoto6;

    @Column(name = "car_photo7", length = 250)
    private String carPhoto7;

    @Column(name = "car_photo8", length = 250)
    private String carPhoto8;

    @Column(name = "car_photo9", length = 250)
    private String carPhoto9;

    @Column(name = "exterior", nullable = false, length = 250)
    private String exterior;

    @Column(name = "interior", nullable = false, length = 250)
    private String interior;

    @Column(name = "odometer", nullable = false, length = 250)
    private String odometer;

    @OneToMany(mappedBy = "carphotoCarPhoto")
    private Set<Car> cars = new LinkedHashSet<>();

}