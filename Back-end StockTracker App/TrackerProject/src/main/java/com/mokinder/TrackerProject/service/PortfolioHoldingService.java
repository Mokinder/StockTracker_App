package com.mokinder.TrackerProject.service;

import com.mokinder.TrackerProject.model.PortfolioHolding;
import com.mokinder.TrackerProject.repository.PortfolioHoldingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioHoldingService {

    @Autowired
    private PortfolioHoldingRepository portfolioHoldingRepository;

    public PortfolioHolding userPortfolio(Long userId) {
        return portfolioHoldingRepository.findById(userId).orElse(null);
    }

    public PortfolioHolding addStock(PortfolioHolding holdings) {
        return portfolioHoldingRepository.save(holdings);
    }

    public PortfolioHolding updateStock(PortfolioHolding holdings) {
        return portfolioHoldingRepository.save(holdings);
    }

    public PortfolioHolding getstock(Long id) {
        return portfolioHoldingRepository.findById(id).orElse(null);
    }

    public void deleteStock(Long id) {
        portfolioHoldingRepository.deleteById(id);
    }

    public List<PortfolioHolding> getAllStocks() {
        return portfolioHoldingRepository.findAll();
    }
}
