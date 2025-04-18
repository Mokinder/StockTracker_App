package com.mokinder.TrackerProject.repository;

import com.mokinder.TrackerProject.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer>{
        Users findByUsername(String username);

        // Option 1: Based on createdAt
        Users findFirstByOrderByCreatedAtDesc();

//        Users findByEmail(String email_id);

}

//    Optional<User> findByEmail(String email_id);
//    Optional<User> findByUsername(String username);
//    Boolean existsByUsername(String username);
//    Boolean existsByEmail(String email_id);
