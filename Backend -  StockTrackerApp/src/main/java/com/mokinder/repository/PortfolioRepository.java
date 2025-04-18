package com.mokinder.repository;


//import com.mokinder.model.Stock;
import com.mokinder.model.PortfolioHoldings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioRepository extends JpaRepository<PortfolioHoldings,Long> {
////    @Query()
////    List<Stock> searchStocks(String keyword);
}
