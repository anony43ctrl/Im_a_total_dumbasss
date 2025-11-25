package com.synechron.group1.onlineretail.service;
 
import com.synechron.group1.onlineretail.domain.User;
import com.synechron.group1.onlineretail.dto.LoginRequest;
import com.synechron.group1.onlineretail.dto.RegisterRequest;
import com.synechron.group1.onlineretail.dto.UpdateProfileRequest;
import com.synechron.group1.onlineretail.dto.UpdatePasswordRequest;
 
public interface UserService {
	public User getUserByEmail(String email);
    User updateUserProfile(String email, UpdateProfileRequest request); // ✅ New method
    void  updateUserPassword(String email, UpdatePasswordRequest request);
	User registerUser(RegisterRequest request);
    User authenticateUser(LoginRequest request);
 
}
 
 
//cHANGEd
 