package com.mokinder.TrackerProject.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mokinder.TrackerProject.model.Stocks;
import com.mokinder.TrackerProject.repository.StockRepository;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class StockService {


    private String twelveDataApi = "https://api.twelvedata.com/stocks";

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private RestTemplate restTemplate;

    public void fetchAndSaveStocks() {
        String url = twelveDataApi + "?exchange=NSE";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode data = root.get("data");

            for (JsonNode stockNode : data) {
                Stocks stock = new Stocks();
                stock.setSymbol(stockNode.get("symbol").asText());
//                stock.setSymbol(stockNode.get("symbol").asText());
                stock.setName(stockNode.get("name").asText());
//                stock.setName(stockNode.get("name").asText());
                stock.setExchange(stockNode.get("exchange").asText());
//                stock.setExchange(stockNode.get("exchange").asText());
                stock.setCountry(stockNode.get("country").asText());

                stockRepository.save(stock);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Stocks> searchStocks(String query) {
        return stockRepository.findByNameContainingIgnoreCase(query);
    }

    public List<Stocks> getAllStocks() {
        return stockRepository.findAll();
    }
}


