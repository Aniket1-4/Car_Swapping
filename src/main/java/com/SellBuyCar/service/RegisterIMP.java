package com.SellBuyCar.service;

import com.SellBuyCar.Interface.IRegister;
import com.SellBuyCar.dto.RegisterDto;
import com.SellBuyCar.model.User;
import com.SellBuyCar.model.Userprofile;
import com.SellBuyCar.repository.UserRepo;
import com.SellBuyCar.repository.UserprofileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
@Service
public class RegisterIMP implements IRegister {
    @Autowired
    private UserprofileRepo userprofileRepo;
    @Autowired
    private UserRepo userRepo;
    @Override
    public String registeration(RegisterDto registerDto) {
        List<Userprofile> list = userprofileRepo.findAll();
        for(int counter=0;counter<list.size();counter++){
            if(((registerDto.getEmail()).equals(list.get(counter).getUser().getEmail()))&&((registerDto.getMobileNo()).equals(list.get(counter).getUser().getMobileNo()))){
                return "Email&Mobile";
            }
            else if((registerDto.getEmail()).equals(list.get(counter).getUser().getEmail())){
                return "Email";
            } else if ((registerDto.getMobileNo()).equals(list.get(counter).getUser().getMobileNo())) {
                return "Mobile";
            }


        }

        byte encrypt[]= Base64.getEncoder().encode(registerDto.getPassword().getBytes());
        String encryptPassword=new String(encrypt);



        User user = new User(registerDto);
        user.setPassword(encryptPassword);
        Userprofile profile = new Userprofile(registerDto);

        profile.setUser(user);
        user.setProfile(profile);
        userRepo.save(user);

        return "Added Data";
    }
}
