package com.synechron.group1.onlineretail.controller;

import com.synechron.group1.onlineretail.domain.User;
import com.synechron.group1.onlineretail.dto.UpdatePasswordRequest;
import com.synechron.group1.onlineretail.dto.UpdateProfileRequest;
import com.synechron.group1.onlineretail.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{email}")
    public ResponseEntity<User> getUser(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

   @PutMapping("/{email}/update")
    public ResponseEntity<User> updateUserProfile(@PathVariable String email,
                                                  @RequestBody UpdateProfileRequest request) {
        return ResponseEntity.ok(userService.updateUserProfile(email, request));
    }

    @PutMapping("/{email}/password")
    public ResponseEntity<String> updateUserPassword(@PathVariable String email,
                                                     @RequestBody UpdatePasswordRequest request) {
        userService.updateUserPassword(email, request);
        return ResponseEntity.ok("Password updated successfully");
    }
}
