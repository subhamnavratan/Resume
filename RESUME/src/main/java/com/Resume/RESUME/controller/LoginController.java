package com.Resume.RESUME.controller;

import com.Resume.RESUME.model.Login;
import com.Resume.RESUME.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginRepository loginRepository;

    // ---------- SIGNUP ----------
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody Login login) {
        // Convert email to lowercase
        login.setEmail(login.getEmail().toLowerCase());

        // Email validation
        if (!login.getEmail().endsWith(".com")) {
            return ResponseEntity.badRequest().body("Email must end with .com");
        }

        // Password validation
        if (login.getPassword().length() <= 5) {
            return ResponseEntity.badRequest().body("Password must be longer than 5 characters");
        }

        // Check if email already exists
        if (loginRepository.findByEmail(login.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Email already registered");
        }

        loginRepository.save(login);
        return ResponseEntity.ok("Signup successful");
    }

    // ---------- SIGNIN ----------
    @PostMapping("/signin")
    public ResponseEntity<String> signin(@RequestBody Login login) {
        // Convert email to lowercase
        String email = login.getEmail().toLowerCase();

        Login existingUser = loginRepository.findByEmail(email);

        if (existingUser == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        if (!existingUser.getPassword().equals(login.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid password");
        }

        return ResponseEntity.ok("Login successful");
    }

}

