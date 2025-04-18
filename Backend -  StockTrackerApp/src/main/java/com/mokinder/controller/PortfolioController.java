package com.mokinder.controller;

import com.mokinder.model.PortfolioHoldings;
//import com.mokinder.model.Stock;
import com.mokinder.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController("/stock-tracker/user/porfolio")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @GetMapping("/{user_id}")
    public ResponseEntity<PortfolioHoldings> userPortfolio(@PathVariable Long user_id){
        return new ResponseEntity<>(portfolioService.userPortfolio(user_id),HttpStatus.OK);
    }

    @PostMapping("/addStock")
    public ResponseEntity<PortfolioHoldings> addStock(@RequestBody PortfolioHoldings holdings){
        return new ResponseEntity<>(portfolioService.addStock(holdings),HttpStatus.CREATED);
    }

    @PutMapping("/update-stock")
    public ResponseEntity<PortfolioHoldings> updateStock(@RequestBody PortfolioHoldings holdings){
        return new ResponseEntity<>(portfolioService.updateStock(holdings),HttpStatus.OK);
    }

    @DeleteMapping("delete-stock/{id}")
    public ResponseEntity<String> deleteStock(@PathVariable Long id){
        PortfolioHoldings holdings = portfolioService.getstock(id);
        if (holdings != null){
            portfolioService.deleteStock(id);
            return new ResponseEntity<>("Deleted..!!",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Stock not found",HttpStatus.NOT_FOUND);
        }
    }




//    @GetMapping("https://api.twelvedata.com/stocks")
//    public Stock getStocks(){
//        return stockService.getStocks();
//    }
//
//    @GetMapping("/stock/search/")
//    public ResponseEntity<List<Stock>> searchStock(@RequestParam String keyword){
//        List<Stock> stocks = stockService.searchStock(keyword);
//        return new ResponseEntity<>(stocks, HttpStatus.OK);
//    }

}
