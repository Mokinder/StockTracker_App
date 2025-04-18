//package com.mokinder.model;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import lombok.Data;
//import org.springframework.stereotype.Component;
//
//import java.security.PrivateKey;
//import java.util.PrimitiveIterator;
//
//@Data
//@Entity
//public class Stock {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long stock_id;
//    private String symbol;
//    private String company_name;
//    private String exchange;
//    private String country;
//
//    public Long getStock_id() {
//        return stock_id;
//    }
//
//    public void setStock_id(Long stock_id) {
//        this.stock_id = stock_id;
//    }
//
//    public String getSymbol() {
//        return symbol;
//    }
//
//    public void setSymbol(String symbol) {
//        this.symbol = symbol;
//    }
//
//    public String getCompany_name() {
//        return company_name;
//    }
//
//    public void setCompany_name(String company_name) {
//        this.company_name = company_name;
//    }
//
//    public String getExchange() {
//        return exchange;
//    }
//
//    public void setExchange(String exchange) {
//        this.exchange = exchange;
//    }
//
//    public String getCountry() {
//        return country;
//    }
//
//    public void setCountry(String country) {
//        this.country = country;
//    }
//
////    @Override
////    public String toString() {
////        return "Stock{" +
////                "stock_id=" + stock_id +
////                ", symbol='" + symbol + '\'' +
////                ", company_name='" + company_name + '\'' +
////                ", exchange='" + exchange + '\'' +
////                ", country='" + country + '\'' +
////                '}';
////    }
//}
//
//
////stock_id integer [primary key, increment]
////symbol varchar [not null, unique]
////company_name varchar [not null]
////exchange varchar [not null]
////country varchar [not null]