package com.mokinder.service;

import com.mokinder.model.Users;
import com.mokinder.repository.loginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private loginRepository loginrepo;

    public String signUp() {
        return " Sign Up Screen..!!";
    }

    public List<Users> getAllUsers() {
        return loginrepo.findAll();
    }

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    public Users register(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        loginrepo.save(user);
        return user;
    }
    public Users getUser(int id) {
        return loginrepo.findById(id).orElse(null);
    }

    public void deleteUser(int id) {
        loginrepo.deleteById(id);
    }
}
//    user_id integer [primary key, increment]
//    username varchar [not null, unique]
//    full_name varchar2 [not null]
//    email varchar [not null, unique]
//    password_hash varchar [not null]
//    registration_date datetime [not null]



//
//    @Qualifier("loginRepository")
//    @Autowired
//    private loginRepository loginrepo;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    public User createUser(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        return loginrepo.save(user);
//    }
//
//    public List<User> getAllUsers() {
//        return loginrepo.findAll();
//    }
//
//    public Optional<User> getUserById(int user_id) {
//        return loginrepo.findById(user_id);
//    }
//
//    public User updateUser(int user_id, User updatedUser) {
//        return loginrepo.findById(user_id)
//                .map(user -> {
//                    user.setUser_name(updatedUser.getUser_name());
//                    user.setEmail_id(updatedUser.getEmail_id());
//                    if(updatedUser.getPassword()!=null && !updatedUser.getPassword().isEmpty()){
//                        user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
//                    }
//
//                    return loginrepo.save(user);
//                })
//                .orElse(null);
//    }
//
//    public void deleteUser(int user_id) {
//        loginrepo.deleteById(user_id);
//    }


//    public User updateUser(int id){
//
//        return "User is updated successfully.."+id;
//    }
//
//    public User deleteUser(int id){
//
//        return id+"user is deleted..";
//    }