package com.mokinder.TrackerProject.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mokinder.TrackerProject.model.StockData;
import com.mokinder.TrackerProject.model.Stocks;
import com.mokinder.TrackerProject.repository.StockDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class StockDataService {

    @Autowired
    private StockDataRepository stockDataRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private String API_KEY = "5O22J6M48MQT903A";




    public void fetchAndStoreStockDataHistory(String symbol) throws Exception {

        String API_URL = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol="
                + symbol + ".BSE&outputsize=full&apikey="+API_KEY;

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(API_URL, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response);
        JsonNode timeSeries = root.path("Time Series (Daily)");

        if (root.has("Error Message")) {
            throw new Exception("Invalid symbol or bad request");
        }

        if (root.has("Note")) {
            throw new Exception("API rate limit exceeded: " + root.get("Note").asText());
        }

        if (timeSeries.isMissingNode()) {
            throw new Exception("Time series data missing from response.");
        }

        LocalDate today = LocalDate.now();
        LocalDate fiveYearsAgo = today.minusYears(5);

        Iterator<Map.Entry<String, JsonNode>> fields = timeSeries.fields();

        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            LocalDate date = LocalDate.parse(entry.getKey());

            if (date.isBefore(fiveYearsAgo)) {
                continue; // Skip older than 5 years
            }

            JsonNode dayData = entry.getValue();

            StockData stock = new StockData();
            stock.setSymbol(symbol);
            stock.setDate(date);
            stock.setOpen_price(dayData.get("1. open").asDouble());
            stock.setHigh_price(dayData.get("2. high").asDouble());
            stock.setLow_price(dayData.get("3. low").asDouble());
            stock.setClose_price(dayData.get("4. close").asDouble());
            stock.setVolume(dayData.get("5. volume").asLong());

            stockDataRepository.save(stock);
        }
    }

    public void fetchAndStoreStockDataLatest(String symbol) throws Exception {
        String API_URL = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY"
                + "&symbol=" + symbol
                + ".BSE&outputsize=compact"
                + "&apikey="+API_KEY;

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(API_URL, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response);
        JsonNode timeSeries = root.path("Time Series (Daily)");

        if (root.has("Error Message")) {
            throw new Exception("Invalid symbol or bad request");
        }

        if (root.has("Note")) {
            throw new Exception("API rate limit exceeded: " + root.get("Note").asText());
        }

        if (timeSeries.isMissingNode()) {
            throw new Exception("Time series data missing from response.");
        }

        // Get the latest date entry
        Iterator<String> dates = timeSeries.fieldNames();
        if (!dates.hasNext()) {
            throw new Exception("No data found.");
        }

        String latestDateStr = dates.next(); // First entry is usually the latest
        JsonNode latestDayData = timeSeries.get(latestDateStr);

        StockData stock = new StockData();
        stock.setSymbol(symbol);
        stock.setDate(LocalDate.parse(latestDateStr));
        stock.setOpen_price(latestDayData.get("1. open").asDouble());
        stock.setHigh_price(latestDayData.get("2. high").asDouble());
        stock.setLow_price(latestDayData.get("3. low").asDouble());
        stock.setClose_price(latestDayData.get("4. close").asDouble());
        stock.setVolume(latestDayData.get("5. volume").asLong());

        // Optional: Check if this date already exists to prevent duplicates
        Optional<StockData> existing = stockDataRepository.findBySymbolAndDate(symbol, stock.getDate());
        if (existing.isEmpty()) {
            stockDataRepository.save(stock);
        }
    }



    public List<StockData> getPriceHistory(String symbol) {
        return stockDataRepository.findBySymbolOrderByDateAsc(symbol);
    }

    public List<StockData> getPriceHistoryBetween(String symbol, LocalDate startDate, LocalDate endDate) {
        return stockDataRepository.findBySymbolAndDateBetweenOrderByDateAsc(symbol, startDate, endDate);
    }

    public String checkIfStockExists(String symbol) {

        List<StockData> matches = stockDataRepository.findBySymbol(symbol);
        if (matches.isEmpty()) {
            return ("Stock not found");
        } else {
            return("Stock exists");
        }
    }
}
