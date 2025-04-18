package com.mokinder.service;

import com.mokinder.model.PortfolioHoldings;
//import com.mokinder.model.Stock;
import com.mokinder.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortfolioService {

    @Autowired
    private PortfolioRepository portfolioRepository;

    public PortfolioHoldings userPortfolio(Long userId) {
        return portfolioRepository.findById(userId).orElse(null);
    }

    public PortfolioHoldings addStock(PortfolioHoldings holdings) {
        return portfolioRepository.save(holdings);
    }

    public PortfolioHoldings updateStock(PortfolioHoldings holdings) {
        return portfolioRepository.save(holdings);
    }

    public PortfolioHoldings getstock(Long id) {
        return portfolioRepository.findById(id).orElse(null);
    }

    public void deleteStock(Long id) {
        portfolioRepository.deleteById(id);
    }
}
