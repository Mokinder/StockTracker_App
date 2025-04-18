package com.mokinder.TrackerProject.service;


import com.mokinder.TrackerProject.model.StockData;
import com.mokinder.TrackerProject.model.WatchListItems;
import com.mokinder.TrackerProject.repository.StockDataRepository;
import com.mokinder.TrackerProject.repository.WatchListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WatchListService {

    @Autowired
    private WatchListRepo watchListRepo;

    @Autowired
    private StockDataRepository stockDataRepository;



    public List<WatchListItems> getWatchlistWithLatestPrices() {
        List<WatchListItems> watchlist = watchListRepo.findAll();
        List<WatchListItems> result = new ArrayList<>();

        for (WatchListItems item : watchlist) {
            String symbol = item.getSymbol();

            // Get the most recent price by date
            StockData latest = stockDataRepository.findTopBySymbolOrderByDateDesc(symbol);
            if (latest != null) {
                WatchListItems dto = new WatchListItems();
                dto.setWatchlist_id(item.getWatchlist_id());
                dto.setSymbol(symbol);
                dto.setStock_name(item.getStock_name()); // Or fetch live
                dto.setPrice(latest.getClose_price());
                result.add(dto);
            }
        }
        return result;
    }


    public Object addtoWatchList(WatchListItems watch) {
        return watchListRepo.save(watch);
    }

    public Optional<WatchListItems> getWatchlistItem(Long id) {
        return watchListRepo.findById(id);
    }

    public void deleteWatchListItem(Long id) {
        watchListRepo.deleteById(id);
    }
}
