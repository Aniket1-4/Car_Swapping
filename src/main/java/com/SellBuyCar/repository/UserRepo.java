package com.SellBuyCar.repository;

import com.SellBuyCar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	public User findById(int id);

   @Query("FROM User u WHERE email = ?1")
	public User findByEmail(String email);

	public User findByResetPasswordToken(String resetPasswordToken);

}
