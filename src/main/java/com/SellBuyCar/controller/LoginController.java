package com.SellBuyCar.controller;

import com.SellBuyCar.dto.ResetPassword;
import com.SellBuyCar.exception.UserNotFoundException;
import com.SellBuyCar.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class LoginController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/forgot", method = RequestMethod.GET)
	public String forgotEmail() {

		return "forgot_email_form";
	}

	@PostMapping("/forgot-password")
	public String forgotPass(HttpServletRequest request) throws UserNotFoundException {

		String email = request.getParameter("email");
		String token = RandomStringUtils.randomAlphabetic(40);

		userService.updateResetPassword(token, email);

		String resetPasswordlink = "http://169.254.63.118:5173/reset-password?token=" + token;

		userService.forgotPass(email, resetPasswordlink);

		return "okk";

	}

	@PostMapping("/reset-password")
	public String resetPassword(@RequestBody ResetPassword resetPassword) throws UserNotFoundException {

		String token = resetPassword.getToken();
		String newPassword = resetPassword.getPassword();

		userService.updatePassword(token, newPassword);

		return "ok";
	}

}
