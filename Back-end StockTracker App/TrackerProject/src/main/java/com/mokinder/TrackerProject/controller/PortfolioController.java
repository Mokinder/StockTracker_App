package com.mokinder.TrackerProject.controller;


import com.mokinder.TrackerProject.model.Portfolio;
import com.mokinder.TrackerProject.model.Users;
import com.mokinder.TrackerProject.service.PortfolioService;
import com.mokinder.TrackerProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stock-tracker/user/")
@CrossOrigin(origins = "*")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @Autowired
    private UserService userService;


    @GetMapping("/portfolio/{id}")
    public ResponseEntity<Portfolio> getPortfolio(@PathVariable int id){
        return new ResponseEntity<>(portfolioService.getPortfolio(id), HttpStatus.FOUND);
    }


    @PostMapping("/create-portfolio")
    public ResponseEntity<Portfolio> createPortfolio(){
        return new ResponseEntity<>(portfolioService.createPortfolio(),HttpStatus.CREATED);
    }

//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<String> deletePortfoliobyId(@PathVariable long id){
//        Portfolio portfolio = portfolioService.getPortfolio(id);
//        if (portfolio != null){
//            portfolioService.deletePortfoliobyId(id);
//            return new ResponseEntity<>("Portfolio Deleted..!!",HttpStatus.OK);
//        }
//        else {
//            return new ResponseEntity<>("Portfolio not found",HttpStatus.NOT_FOUND);
//        }
//    }

}
