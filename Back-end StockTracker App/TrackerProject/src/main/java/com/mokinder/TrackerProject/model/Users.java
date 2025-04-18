package com.mokinder.TrackerProject.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    @NotBlank
    @Size(max = 10)
    @Column(unique = true,nullable = false)
    private String username;

    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
    private Portfolio portfolio;

    @Size(max = 20)
    private String full_name ;

    @NotBlank
    @Size(max = 50)
    @Email
    @JsonProperty("email_id")
 //   @Column(unique = true, nullable = false)
    private String email_id;

    @NotBlank
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();  // Set current timestamp on insert
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getDatetime() {
        return createdAt;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.createdAt = datetime;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public String getFullName() {
        return full_name;
    }

    // Setter
    public void setFullName(String full_name) {
        this.full_name = full_name;
    }

    @Override
    public String toString() {
        return "Users{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", portfolio=" + portfolio +
                ", full_name='" + full_name + '\'' +
                ", email_id='" + email_id + '\'' +
                ", password='" + password + '\'' +
                ", datetime=" + createdAt +
                '}';
    }

}
