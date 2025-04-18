package com.mokinder.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
public class PortfolioHoldings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long holding_id;
    private long portfolio_id;

    private long stock_id;

    private long fund_id;

    private double quantity;

    private double purchase_price;

    private Date purchase_date;


    public long getHolding_id() {
        return holding_id;
    }

    public void setHolding_id(long holding_id) {
        this.holding_id = holding_id;
    }

    public long getPortfolio_id() {
        return portfolio_id;
    }

    public void setPortfolio_id(long portfolio_id) {
        this.portfolio_id = portfolio_id;
    }

    public long getStock_id() {
        return stock_id;
    }

    public void setStock_id(long stock_id) {
        this.stock_id = stock_id;
    }

    public long getFund_id() {
        return fund_id;
    }

    public void setFund_id(long fund_id) {
        this.fund_id = fund_id;
    }

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

}


//Table portfolioHoldings{
//holding_id integer [primary key, increment]
//portfolio_id integer [not null]
//stock_id integer
//fund_id integer
//quantity decimal [not null]
//purchase_price decimal [not null]
//purchase_date date
//}
