package com.example.libraryIt;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByTitleContainingIgnoreCase(String title);
    List<Item> findAllByUser(User user);
    List<Item> findAllByReservedUser(User user);
    List<Item> findTop3ByOrderByTimesLoanedDesc();

}