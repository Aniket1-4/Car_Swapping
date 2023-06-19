package com.SellBuyCar.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDto {


    public String address;

    private String city;

    private String firstName;

    private String lastName;
    private String email;

    private String mobileNo;

    private String password;
}
