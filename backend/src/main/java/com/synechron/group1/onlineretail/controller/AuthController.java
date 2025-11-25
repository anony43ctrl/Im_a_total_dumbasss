package com.synechron.group1.onlineretail.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import com.synechron.group1.onlineretail.domain.User;
import com.synechron.group1.onlineretail.dto.LoginRequest;
import com.synechron.group1.onlineretail.dto.RegisterRequest;
import com.synechron.group1.onlineretail.service.UserService;
 
@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {
 
    @Autowired private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.registerUser(request));
    }
 
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.authenticateUser(request));
    }
}
 
 
//new file
