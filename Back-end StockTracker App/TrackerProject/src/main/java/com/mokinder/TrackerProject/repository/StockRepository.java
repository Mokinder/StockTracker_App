package com.mokinder.TrackerProject.repository;

import com.mokinder.TrackerProject.model.Stocks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stocks,Long> {
    List<Stocks> findByNameContainingIgnoreCase(String query);

}
