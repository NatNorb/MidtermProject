package com.ironhack.MidtermProject.repository.additional;

import com.ironhack.MidtermProject.dao.additional.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ironhack.MidtermProject.dao.additional.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

public interface TestsRepository extends JpaRepository<Transaction, Long> {

    @Modifying
    @Transactional
    @Query(value = "alter table account auto_increment = 1", nativeQuery = true)
    void alterTableAccount();

    @Modifying
    @Transactional
    @Query(value = "alter table address auto_increment = 1", nativeQuery = true)
    void alterTableAddress();

    @Modifying
    @Transactional
    @Query(value = "alter table transaction auto_increment = 1", nativeQuery = true)
    void alterTableTransaction();

    @Modifying
    @Transactional
    @Query(value = "alter table account_holder auto_increment = 1", nativeQuery = true)
    void alterTableAccountHolder();

    @Modifying
    @Transactional
    @Query(value = "alter table third_party auto_increment = 1", nativeQuery = true)
    void alterTableThirdParty();

    @Modifying
    @Transactional
    @Query(value = "alter table user auto_increment = 1", nativeQuery = true)
    void alterTableUser();

    @Modifying
    @Transactional
    @Query(value = "alter table role auto_increment = 1", nativeQuery = true)
    void alterTableRole();
}
