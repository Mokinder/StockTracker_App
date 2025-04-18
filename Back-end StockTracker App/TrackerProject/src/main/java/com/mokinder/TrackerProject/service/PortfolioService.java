package com.mokinder.TrackerProject.service;

import com.mokinder.TrackerProject.model.Portfolio;
import com.mokinder.TrackerProject.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

@Service
public class PortfolioService {

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private UserService userService;


    public Portfolio getPortfolio(int id){
        return portfolioRepository.findById((long) id).orElse(null);
    }


    public Portfolio createPortfolio() {
        Portfolio portfolio = new Portfolio();
        portfolio.setPortfolio_name(userService.getLatestUserFullName());
        return portfolioRepository.save(portfolio);
    }

//    public void deletePortfoliobyId(long id) {
//         portfolioRepository.deleteById(id);
//    }

    // public Long getUserId() {
   // }
}


//Table userPortfolios{
//portfolio_id integer [primary key, increment]
//user_id int []
//portfolio_name varchar
//creation_date datetime
//}
