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
@Table(name = "dealer")
public class Dealer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Dealer_id")
    private Integer id;

    @Column(name = "address")
    private String address;

    @Column(name = "adhar_shopact", nullable = false, length = 250)
    private String adharShopact;

    @Column(name = "area", nullable = false, length = 45)
    private String area;

    @Column(name = "city", nullable = false, length = 45)
    private String city;

    @Column(name = "fristname", length = 45)
    private String fristname;

    @Column(name = "last_name", length = 45)
    private String lastName;

    @Column(name = "mobile_no", nullable = false, length = 45)
    private String mobileNo;

    @Column(name = "shop_name", nullable = false, length = 250)
    private String shopName;
    @Column(name = "Email",nullable = false)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_user_id", nullable = false)
    private User userUser;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bidding_bidding_id", nullable = false)
    private Bidding biddingBidding;

    @OneToMany(mappedBy = "dealerVendor")
    private Set<Biddingbuy> biddingbuys = new LinkedHashSet<>();

    @OneToMany(mappedBy = "dealerVendor")
    private Set<Car> cars = new LinkedHashSet<>();

}