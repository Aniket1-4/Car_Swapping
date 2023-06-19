package com.SellBuyCar.controller;

import com.SellBuyCar.Interface.IRegister;
import com.SellBuyCar.dto.RegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class RegisterController {
    @Autowired
    private IRegister iRegister;
    @PostMapping("/userregister")
    public ResponseEntity<?> userRegisteration(@RequestBody RegisterDto registerDto){
//        System.out.println("11");
        String responce=iRegister.registeration(registerDto);
        if(responce.equals("Email&Mobile")){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("email & mobile already exist");
        }
        else if(responce.equals("Email")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("email already exist");

        } else if (responce.equals("Mobile")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("mobile already exist");

        }

        return ResponseEntity.status(HttpStatus.CREATED).body("account has been created");


    }
}
