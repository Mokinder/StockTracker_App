//package com.mokinder.TrackerProject.scheduler;
//
//
//import com.mokinder.TrackerProject.model.PortfolioHolding;
//import com.mokinder.TrackerProject.repository.PortfolioHoldingRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class StockPriceScheduledTask {
//
//
//    @Autowired
//    PortfolioHoldingRepository portfolioHoldingRepository;
//
//
//    @Scheduled(cron = "0 0 18 * * ?")
//    public void fetchDailyPrices() {
//        System.out.println("Running scheduled job to fetch latest stock prices at 6 PM");
//
//        List<PortfolioHolding> holdings = portfolioHoldingRepository.findAll();
//
//        for (PortfolioHolding holding : holdings) {
//            String symbol = holding.ge();
//
//            try {
//                stockService.fetchAndStoreLatestPrice(symbol);
//                System.out.println("✅ Fetched price for: " + symbol);
//            } catch (Exception e) {
//                System.err.println("❌ Failed to fetch for: " + symbol + " | " + e.getMessage());
//            }
//        }
//    }
//}
