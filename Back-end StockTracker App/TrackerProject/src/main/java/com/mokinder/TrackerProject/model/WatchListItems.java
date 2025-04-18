package com.mokinder.TrackerProject.model;


import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;


@Entity
@Data
public class WatchListItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int watchlist_item_id;

    private String symbol;

    private double price;

    private String stock_name;

    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

        public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public int getWatchlist_id() {
        return watchlist_item_id;
    }

    public void setWatchlist_id(int watchlist_item_id) {
        this.watchlist_item_id = watchlist_item_id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getcreatedAt() {
        return createdAt;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.createdAt = dateTime;
    }
}

//Table watchlistItems {
//watchlist_item_id integer [primary key, increment]
//watchlist_id integer
//stock_id integer
//fund_id integer
//added_date datetime
//}
