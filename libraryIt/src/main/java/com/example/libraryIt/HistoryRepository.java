package com.example.libraryIt;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findAllByItemId(Long itemId);
    List<History> findAllByUserId(Long userId);
    History findOneByUserIdAndItemIdAndReturnedOnIsNull(Long userId, Long itemId);
    
}