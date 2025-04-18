package com.mokinder.TrackerProject.controller;

import com.mokinder.TrackerProject.model.Users;
import com.mokinder.TrackerProject.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@CrossOrigin(origins = "*")  // Allow requests from Flutter (localhost/mobile)
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> loginScreen(@RequestBody Users user){
       // return new ResponseEntity<>(userService.verifyUser(user), HttpStatus.ACCEPTED);
        try {
            String result = userService.verifyUser(user);
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED); // Success - 200
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED); // 401
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR); // 500
        }
    }

    @PostMapping("/register-new")
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        return new ResponseEntity<>(userService.register(user), HttpStatus.CREATED);
    }
}
