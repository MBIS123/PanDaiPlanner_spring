package com.FYP.PandaiPlanner.repository;

import com.FYP.PandaiPlanner.entity.Budget;
import com.FYP.PandaiPlanner.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT b FROM Transaction b WHERE b.user.id = :userId ")
    List<Transaction> findAllByUserId(Long userId);


}
