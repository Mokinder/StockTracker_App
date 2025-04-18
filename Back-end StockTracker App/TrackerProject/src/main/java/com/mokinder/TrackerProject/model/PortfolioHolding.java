package com.mokinder.TrackerProject.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;


import java.sql.Date;

@Entity
public class PortfolioHolding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long holding_id;
    @OneToOne
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    private String stock_name;


    //    private long stock_id;
//    private long fund_id;
    private double quantity;
    private double purchase_price;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date purchase_date;


    public long getHolding_id() {
        return holding_id;
    }

    public void setHolding_id(long holding_id) {
        this.holding_id = holding_id;
    }

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

//    public long getStock_id() {
//        return stock_id;
//    }
//
//    public void setStock_id(long stock_id) {
//        this.stock_id = stock_id;
//    }
//
//    public long getFund_id() {
//        return fund_id;
//    }
//
//    public void setFund_id(long fund_id) {
//        this.fund_id = fund_id;
//    }

    public double getPurchase_price() {
        return purchase_price;
    }

    public void setPurchase_price(double purchase_price) {
        this.purchase_price = purchase_price;
    }

    public Date getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(Date purchase_date) {
        this.purchase_date = purchase_date;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }
}
