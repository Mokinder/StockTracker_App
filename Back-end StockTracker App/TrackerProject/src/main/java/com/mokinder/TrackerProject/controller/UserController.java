package com.mokinder.TrackerProject.controller;

import com.mokinder.TrackerProject.model.Users;
import com.mokinder.TrackerProject.service.UserService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock-tracker/user")
@CrossOrigin(origins = "*")
public class UserController {

        @Autowired
        private UserService userService;

        @GetMapping("/")
        public String loginDone(HttpServletRequest Request){
            return "Login Done successfully..!!"+ Request.getSession().getId();
        }

//        @PostMapping("/login")
//        public ResponseEntity<String> loginScreen(@RequestBody Users user){
//            return new ResponseEntity<>(userService.verifyUser(user),HttpStatus.ACCEPTED);
//        }

        @GetMapping("/Csrf-token")
        public CsrfToken getCsrfTolken(HttpServletRequest request){
            return (CsrfToken) request.getAttribute("_csrf");
        }
        @GetMapping("/signup")
        public String signUp(){
            return userService.signUp();
        }

        @GetMapping("/all")
        public ResponseEntity<List<Users>> getAllUsers(){
            return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Users> getUser(@PathVariable int id){
            Users user = userService.getUser(id);
            if (user != null){
                return new ResponseEntity<>(user,HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @PutMapping("/update-user")
        public ResponseEntity<Users> updateUser(@RequestBody Users user){
            return new ResponseEntity<>(userService.updateUser(user),HttpStatus.OK);
        }

       @DeleteMapping("/delete/{id}")
        public ResponseEntity<String> deleteUser(@PathVariable int id){
             Users user = userService.getUser(id);
             if (user != null){
                 userService.deleteUser(id);
                 return new ResponseEntity<>("Deleted..!!",HttpStatus.OK);
             }
             else {
                 return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
             }
        }
}

//        @PostMapping("/register")
//        public String registerUser(@RequestBody Users user) {
//            if (userService.findByEmail(user.getEmail()) != null) {
//                return "Email already registered!";
//            }
//            userService.registerUser(user);
//            return "User registered successfully!";
//        }

//        @PostMapping("/login")
//        public String loginUser(@RequestBody Users user) {
//            Users foundUser = userService.findByEmail(user.getEmail_id());
//            if (foundUser != null && new BCryptPasswordEncoder().matches(user.getPassword(), foundUser.getPassword())) {
//                return "Login successful!";
//            } else {
//                return "Invalid credentials!";
//            }
//        }
