package com.mokinder.TrackerProject.repository;

import com.mokinder.TrackerProject.model.PortfolioHolding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioHoldingRepository extends JpaRepository<PortfolioHolding,Long> {

}
