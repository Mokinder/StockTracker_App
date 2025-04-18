package com.mokinder.TrackerProject.controller;

import com.mokinder.TrackerProject.model.WatchListItems;
import com.mokinder.TrackerProject.service.WatchListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stock-tracker/user/watch-list")
public class WatchListContoller {

    @Autowired
    private WatchListService watchListService;

    @GetMapping("/fetch-list")
    public ResponseEntity<List<WatchListItems>> getWatchlist() {
        return new ResponseEntity<>(watchListService.getWatchlistWithLatestPrices(), HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<String> addToWatchlist(@RequestParam String symbol, @RequestParam String stockName) {
        WatchListItems watch = new WatchListItems();
        watch.setSymbol(symbol);
        watch.setStock_name(stockName);
        watchListService.addtoWatchList(watch);
        return new ResponseEntity<>("Added to watchlist", HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        Optional<WatchListItems> item = watchListService.getWatchlistItem(id);
        if (item.isPresent()){
            watchListService.deleteWatchListItem(id);
            return new ResponseEntity<>("Deleted..!!",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Watchlist Item not found",HttpStatus.NOT_FOUND);
        }
    }

}
