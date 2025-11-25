
package com.synechron.group1.onlineretail.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.synechron.group1.onlineretail.domain.User;
import com.synechron.group1.onlineretail.dto.LoginRequest;
import com.synechron.group1.onlineretail.dto.RegisterRequest;
import com.synechron.group1.onlineretail.dto.UpdatePasswordRequest;
import com.synechron.group1.onlineretail.dto.UpdateProfileRequest;
import com.synechron.group1.onlineretail.enums.Role;
import com.synechron.group1.onlineretail.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User registerUser(RegisterRequest request) {
		if (userRepository.findByEmail(request.getEmail()).isPresent()) {
			throw new RuntimeException("Email already registered");
		}

		User user = new User();
		user.setFullname(request.getFullname());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setPhoneNumber(request.getPhoneNumber());
		user.setAddress(request.getAddress());
		user.setRole(Role.CUSTOMER); // default role

		return userRepository.save(user);
	}

	@Override
	public User authenticateUser(LoginRequest request) {
		Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
		if (userOpt.isEmpty()) {
			throw new RuntimeException("Invalid email or password");
		}

		User user = userOpt.get();
		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new RuntimeException("Invalid email or password");
		}

		return user;
	}

	// ✅ Fetch user by email
	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found with email: " + email));
	}

	// ✅ Update profile

	@Override
	public User updateUserProfile(String email, UpdateProfileRequest request) {
		User user = getUserByEmail(email);
		user.setFullname(request.getFullname());
		user.setPhoneNumber(request.getPhoneNumber());
		user.setAddress(request.getAddress());
		return userRepository.save(user);
	}

	@Override
	public void updateUserPassword(String email, UpdatePasswordRequest request) {
		User user = getUserByEmail(email);

		if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
			throw new IllegalArgumentException("Old password is incorrect");
		}

		user.setPassword(passwordEncoder.encode(request.getNewPassword()));
		userRepository.save(user);
	}

	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

}
