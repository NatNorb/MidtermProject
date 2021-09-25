package com.ironhack.MidtermProject.repository.additional;

import com.ironhack.MidtermProject.dao.additional.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "SELECT MAX(timestamp)  FROM transaction WHERE acc_id = :id and operations = 'INTEREST' ", nativeQuery = true)
    LocalDateTime dateOfLastInterest(@Param("id") long id);

    @Query(value = "WITH MinTime AS (SELECT MIN(timestamp) AS min_time FROM transaction) " +
            "SELECT distinct acc_id FROM MinTime MT CROSS JOIN transaction T " +
            "GROUP BY acc_id,  DATE_ADD(MT.min_time, interval TIME_TO_SEC(timediff(timestamp, min_time)) second) " +
            "HAVING count(*)>2 ",  nativeQuery = true)
   // Optional<Long> countTransaction();
    List<Optional<Long>> countTransaction();


    @Query(value = "SELECT acc_id from transaction " +
            "where date(timestamp) between DATE_ADD(date(sysdate()), interval -2 day) and DATE_ADD(date(sysdate()), interval -1 day) " +
            "group by acc_id " +
            "having sum(case when date(timestamp) = DATE_ADD(date(sysdate()), interval -1 day) then abs(value) else 0 end) > " +
            "sum(case when date(timestamp) = DATE_ADD(date(sysdate()), interval -2 day) then abs(value) else 0 end)*1.5 = 1 ",  nativeQuery = true)
    List<Optional<Long>> listOfAccId();
//
//
//                   "SELECT acc_id, COUNT(*) AS count " +
//                   "FROM MinTime MT CROSS JOIN transaction T " +
//                   "GROUP BY acc_id, DATE_ADD(MT.min_time, interval TIME_TO_SEC(timediff(timestamp, min_time)) second)", nativeQuery = true)


}
