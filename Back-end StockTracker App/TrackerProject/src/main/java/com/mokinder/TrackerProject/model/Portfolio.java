package com.mokinder.TrackerProject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long portfolio_id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @OneToOne(mappedBy = "portfolio", cascade = CascadeType.ALL)
    private PortfolioHolding portfolioHolding;

    @NotBlank
    @Size(max = 10)
    private String portfolio_name;

    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();  // Set current timestamp on insert
    }

    public Long getPortfolio_id() {
        return portfolio_id;
    }

    public void setPortfolio_id(Long portfolio_id) {
        this.portfolio_id = portfolio_id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String getPortfolio_name() {
        return portfolio_name;
    }

    public void setPortfolio_name(String portfolio_name) {
        this.portfolio_name = portfolio_name;
    }

    public LocalDateTime getDate() {
        return createdAt;
    }

    public void setDate(LocalDateTime datetime) {
        this.createdAt = datetime;
    }

    public PortfolioHolding getPortfolioHolding() {
        return portfolioHolding;
    }

    public void setPortfolioHolding(PortfolioHolding portfolioHolding) {
        this.portfolioHolding = portfolioHolding;
    }
}
