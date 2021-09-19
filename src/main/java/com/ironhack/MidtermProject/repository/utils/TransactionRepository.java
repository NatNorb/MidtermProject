package com.ironhack.MidtermProject.repository.utils;

import com.ironhack.MidtermProject.dao.utils.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "SELECT COUNT(*) FROM transaction", nativeQuery = true)
    int numberOfTransactionByAccountId();

    @Query(value = "SELECT MAX(timestamp)  FROM transaction WHERE acc_id = :id and operations = 'INTEREST' ", nativeQuery = true)
    LocalDateTime dateOfLastInterest(@Param("id") long id);

}
