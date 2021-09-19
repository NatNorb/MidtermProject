package com.ironhack.MidtermProject.repository.accounts;

import com.ironhack.MidtermProject.dao.accounts.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByPrimaryOwner(String primaryOwner);
}
