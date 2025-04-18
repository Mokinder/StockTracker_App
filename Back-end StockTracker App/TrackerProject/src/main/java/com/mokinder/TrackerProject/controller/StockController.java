package com.mokinder.TrackerProject.controller;


import com.mokinder.TrackerProject.config.SSLUtilities;
import com.mokinder.TrackerProject.model.StockData;
import com.mokinder.TrackerProject.model.Stocks;
import com.mokinder.TrackerProject.repository.StockDataRepository;
import com.mokinder.TrackerProject.service.StockDataService;
import com.mokinder.TrackerProject.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stocks")
@CrossOrigin(origins = "*")
public class StockController {


    @Autowired
    private StockService stockService;

    @Autowired
    private StockDataService stockDataService;


    @Autowired
    SSLUtilities sslUtilities;


    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/sync")
    public ResponseEntity<String> syncStocks() {
        SSLUtilities.disableCertificateValidation();
        stockService.fetchAndSaveStocks();
        return new ResponseEntity<>("Stock data synced!",HttpStatus.OK);
    }

    @GetMapping("/show-all")
    public ResponseEntity<List<Stocks>> getAllStocks() {
        return new ResponseEntity<>(stockService.getAllStocks(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Stocks>> searchStocks(@RequestParam String query) {
        return new ResponseEntity<>(stockService.searchStocks(query),HttpStatus.FOUND);
    }

    // <--ThirdParty - Integration -->
    @GetMapping("/fetch-stock-data")
    public ResponseEntity<String> fetchIndividualStockData(@RequestParam String symbol){
        SSLUtilities.disableCertificateValidation();
        try {
            stockDataService.fetchAndStoreStockDataHistory(symbol);
            return new ResponseEntity<>("Data fetched and stored successfully for symbol: " + symbol,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(),HttpStatus.BAD_REQUEST) ;
        }
    }

    // <--ThirdParty - Integration -->
    @GetMapping("/fetch-stock-data/latest")
    public ResponseEntity<String> fetchIndividualStockDataLatest(@RequestParam String symbol){
        SSLUtilities.disableCertificateValidation();
        try {
            stockDataService.fetchAndStoreStockDataLatest(symbol);
            return new ResponseEntity<>("Data fetched and stored successfully for symbol: " + symbol,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(),HttpStatus.BAD_REQUEST) ;
        }
    }

    // <-- Fetch the data from DB -->
    @GetMapping("/history")
    public List<StockData> getStockHistory(
            @RequestParam String symbol,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        if (startDate != null && endDate != null) {
            return stockDataService.getPriceHistoryBetween(symbol, startDate, endDate);
        } else {
            return stockDataService.getPriceHistory(symbol);
        }
    }

    @GetMapping("/check")
    public ResponseEntity<String> checkIfStockExists(@RequestParam String symbol) {
        String result = stockDataService.checkIfStockExists(symbol);
        if (result == "Stock exists"){
            return new ResponseEntity<>("Found..",HttpStatus.FOUND);
        }else {
            try {
                SSLUtilities.disableCertificateValidation();
                stockDataService.fetchAndStoreStockDataHistory(symbol);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return new ResponseEntity<>("Now fetched..",HttpStatus.OK);
        }
    }



//    @GetMapping("/")
//    public ResponseEntity<List<Stocks>> getStocksList() throws Exception {
//        SSLUtilities.disableCertificateValidation();
//        List<Stocks> stocks = stockService.getStocksList();
//        return new ResponseEntity<>(stocks, HttpStatus.OK);
//    }


}

