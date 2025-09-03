package com.Resume.RESUME.service;

import com.Resume.RESUME.model.Login;
import com.Resume.RESUME.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    // ---------- SIGNUP ----------
    public String signup(Login login) {
        if (!login.getEmail().endsWith(".com")) {
            return "Email must end with .com";
        }

        if (login.getPassword().length() <= 5) {
            return "Password must be longer than 5 characters";
        }

        if (loginRepository.findByEmail(login.getEmail()) != null) {
            return "Email already registered";
        }

        loginRepository.save(login);
        return "Signup successful";
    }

    // ---------- LOGIN ----------
    public String login(Login login) {
        Login existingUser = loginRepository.findByEmail(login.getEmail());

        if (existingUser == null) {
            return "User not found";
        }

        if (!existingUser.getPassword().equals(login.getPassword())) {
            return "Invalid password";
        }

        return "Login successful";
    }
}
