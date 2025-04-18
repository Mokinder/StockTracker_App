package com.mokinder.TrackerProject.repository;

import com.mokinder.TrackerProject.model.WatchListItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WatchListRepo extends JpaRepository<WatchListItems,Long> {

    List<WatchListItems> findAll();
}
