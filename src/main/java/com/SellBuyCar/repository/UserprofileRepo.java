package com.SellBuyCar.repository;

import com.SellBuyCar.model.Userprofile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserprofileRepo extends JpaRepository<Userprofile,Integer> {

}
