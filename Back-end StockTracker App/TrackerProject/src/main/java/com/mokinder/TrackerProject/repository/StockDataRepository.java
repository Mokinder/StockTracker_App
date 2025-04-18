package com.mokinder.TrackerProject.repository;

import com.mokinder.TrackerProject.model.StockData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface StockDataRepository extends JpaRepository<StockData,Long> {

    List<StockData> findBySymbol(String symbol);

    List<StockData> findBySymbolOrderByDateAsc(String symbol);

    Optional<StockData> findBySymbolAndDate(String symbol, LocalDate date);

    // Optional: filter by date range
    List<StockData> findBySymbolAndDateBetweenOrderByDateAsc(String symbol, LocalDate startDate, LocalDate endDate);

    StockData findTopBySymbolOrderByDateDesc(String symbol);
}
