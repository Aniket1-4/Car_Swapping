package com.SellBuyCar.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.SellBuyCar.exception.UserNotFoundException;
import com.SellBuyCar.model.User;
import com.SellBuyCar.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public void updateResetPassword(String token, String email) throws UserNotFoundException {

		User user = userRepo.findByEmail(email);

		if (user != null) {

			user.setResetPasswordToken(token);
			userRepo.save(user);

		} else {

			throw new UserNotFoundException("could not find any user with this email");

		}

	}

	@Override
	public void forgotPass(String email, String resetPasswordLink) throws UserNotFoundException {

		// Set the email message content
		String message = "Hello this is Aniket";

		// Set the password reset link
		String resetLink = resetPasswordLink;

		// Set the email subject
		String subject = "Checking: confirmation";

		// Set the sender's email address
		String from = "b.aniket1414@gmail.com";

		// Set the recipient's email address
		String to = email;

		// Send the email using the sendEmail() method
		sendEmail(message, subject, to, from, resetLink);

	}

	// @Override
	public void sendEmail(String message, String subject, String to, String from, String resetLink) {
		// SMTP server for Gmail
		String host = "smtp.gmail.com";

		// Getting the system properties
		Properties properties = System.getProperties();

		System.out.println(properties);

		// Setting important information to the properties object
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// Creating a session with the properties and an authenticator
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// Return the email address and password for authentication
				return new PasswordAuthentication("b.aniket1414@gmail.com", "egmqlowlfodymfzw");
			}

		});

		// Composing the email content
		String content = "To reset your password click hear " + resetLink;

		// Creating a MimeMessage object for the session
		MimeMessage m = new MimeMessage(session);

		try {
			// Setting the sender of the email
			m.setFrom(from);

			// Adding the recipient to the message
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Adding the subject to the message
			m.setSubject(subject);

			// Adding the content to the message
			m.setText(content);

			// Sending the message
			Transport.send(m);

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	public User get(String resetPasswordToken) {

		return userRepo.findByResetPasswordToken(resetPasswordToken);
	}

	public void updatePassword(String token, String newPassword) {

		// Find the user based on the reset password token
		User user = userRepo.findByResetPasswordToken(token);

		// Create an instance of BCryptPasswordEncoder to encode the new password
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

		// Encode the new password using BCryptPasswordEncoder
		String encodedPassword = bCryptPasswordEncoder.encode(newPassword);

		// Update the user's password with the encoded password
		user.setPassword(encodedPassword);

		// Clear the reset password token as it is no longer needed
		user.setResetPasswordToken(null);

		// Save the updated user object in the repository
		userRepo.save(user);
	}

}
