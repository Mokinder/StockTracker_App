package com.mokinder.TrackerProject.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stocks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long stock_id;

//    @OneToMany(mappedBy = "stocks", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<StockData> stockData;

    @Column(nullable = false)
    private String symbol;

    @Column(name = "company_name")
    private String name;

    private String exchange;

    private String country;

    public long getStock_id() {
        return stock_id;
    }

    public void setStock_id(long stock_id) {
        this.stock_id = stock_id;
    }

//    public List<StockData> getStockData() {
//        return stockData;
//    }
//
//    public void setStockData(List<StockData> stockData) {
//        this.stockData = stockData;
//    }

    public String getSymbol() {
        return this.symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Stocks{" +
                "stock_id=" + stock_id +
                ", symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", exchange='" + exchange + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
//
//
//
