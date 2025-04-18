package com.mokinder.TrackerProject.service;

import com.mokinder.TrackerProject.model.Users;
import com.mokinder.TrackerProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthenticationManager authManager;

//    @Autowired
//    private JWTService jwtService;

    public String signUp() {
        return " Sign Up Screen..!!";
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    public Users register(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }
    public Users getUser(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public Users updateUser(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public String verifyUser(Users user) {
//        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
//        if (authentication.isAuthenticated()) {
//            return "Login Success..";
////            return jwtService.generateToken(user.getUsername());
//        } else {
//            return "400";
//        }

        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
            if (authentication.isAuthenticated()) {
                return "Login Success.."; // Or return a JWT here
            }
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
        throw new BadCredentialsException("Invalid username or password");
    }

    public String getLatestUserFullName() {
        Users latestUser = userRepository.findFirstByOrderByCreatedAtDesc();
        return latestUser.getFullName();
    }

//    public Users findByEmail(String email) {
//        return userRepository.findByEmail(email);
//    }
}
