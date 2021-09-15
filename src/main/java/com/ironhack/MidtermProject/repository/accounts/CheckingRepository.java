package com.ironhack.MidtermProject.repository.accounts;

import com.ironhack.MidtermProject.dao.accounts.Checking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingRepository extends JpaRepository<Checking, Long> {

}
