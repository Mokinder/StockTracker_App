package com.mokinder.TrackerProject.controller;

import com.mokinder.TrackerProject.model.PortfolioHolding;
import com.mokinder.TrackerProject.service.PortfolioHoldingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock-tracker/user/porfolio-holdings")
@CrossOrigin(origins = "*")
public class PortfolioHoldingController {
    @Autowired
    private PortfolioHoldingService portfolioHoldingService;

    @GetMapping("/{user_id}")
    public ResponseEntity<PortfolioHolding> userPortfolio(@PathVariable Long user_id){
        return new ResponseEntity<>(portfolioHoldingService.userPortfolio(user_id), HttpStatus.OK);
    }

    @GetMapping("/all-holdings")
    public ResponseEntity<List<PortfolioHolding>> getAllStocks() {
        return new ResponseEntity<>(portfolioHoldingService.getAllStocks(),HttpStatus.OK);
    }

    @PostMapping("/addStock")
    public ResponseEntity<PortfolioHolding> addStock(@RequestBody PortfolioHolding holdings){
        return new ResponseEntity<>(portfolioHoldingService.addStock(holdings),HttpStatus.CREATED);
    }

    @PutMapping("/update-stock")
    public ResponseEntity<PortfolioHolding> updateStock(@RequestBody PortfolioHolding holdings){
        return new ResponseEntity<>(portfolioHoldingService.updateStock(holdings),HttpStatus.OK);
    }

    @DeleteMapping("delete-stock/{id}")
    public ResponseEntity<String> deleteStock(@PathVariable Long id){
        PortfolioHolding holdings = portfolioHoldingService.getstock(id);
        if (holdings != null){
            portfolioHoldingService.deleteStock(id);
            return new ResponseEntity<>("Deleted..!!",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Stock not found",HttpStatus.NOT_FOUND);
        }
    }
}
