package com.mokinder.controller;
import com.mokinder.model.Users;
import com.mokinder.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/user")
public class LoginController {

    @Autowired
    private UserService userService;


    @GetMapping("/")
    public String login(){
        return "Login Screen..!!";
    }

    @GetMapping("/signup")
    public String signUp(){
        return userService.signUp();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Users>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
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

    @PostMapping("/register-new")
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
          return new ResponseEntity<>(userService.register(user), HttpStatus.CREATED);
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


//@PostMapping("/register")
//public Users register(@RequestBody Users user) {
//    return service.register(user);

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
//        Optional<User> userOpt = userService.findByEmail(email);
//        String unsuccess = "Incorrect password Or User not found ";
//        if (userOpt.isPresent()) {
//            User user = userOpt.get();
//            String success = "Login successful for " + user.getEmail_id();
//            if (user.getPassword().equals(password)) {
//                return new ResponseEntity<>(success,HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(unsuccess,HttpStatus.NOT_FOUND);
//            }
//        }
//        return new ResponseEntity<>(unsuccess,HttpStatus.NOT_FOUND);
//    }

//    @Autowired
//    private UserService userService;
//
//    @PostMapping
//    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
//        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<User>> getAllUsers() {
//        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable int id) {
//        Optional<User> user = userService.getUserById(id);
//        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
//                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<User> updateUser(@PathVariable int id, @Valid @RequestBody User user) {
//        User updatedUser = userService.updateUser(id, user);
//        return updatedUser != null ? new ResponseEntity<>(updatedUser, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
//        userService.deleteUser(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}






















//@RestController
//@RequestMapping("/user")
//public class LoginController {
//
//    @Autowired
//    UserService service;
//
//    @GetMapping("/login")
//    public String loginUser(@PathVariable String email, String password){
//        return service.loginUser(email,password);
//    }
//
////    @GetMapping("/login/{id}")
////    public String login_username(@PathVariable int id){
////        return "USer_id is "+id;
////    }
//    @GetMapping("/signup")
//    public String signUp(){
//        return service.signUp();
//    }
//
//    @GetMapping("/list-user")
//    public String getUsers(){
//        return service.getUsers();
//    }
//    @PostMapping("/signup/create-user")
//    public String createUser(){
//        return service.createUSer();
//    }
//
//    @PutMapping("/update{id}")
//    public String updateUser(@PathVariable int id){
//        return service.updateUser(id);
//    }
//
//    @DeleteMapping("/delete{id}")
//    public String deleteUser(@PathVariable int id){
//        return service.deleteUser(id);
//    }
//
//}
