package com.ironhack.MidtermProject.service.interfaces;

import com.ironhack.MidtermProject.dao.Money;
import com.ironhack.MidtermProject.dao.accounts.Account;

import java.math.BigDecimal;

public interface IAccountService {

    void deposit(Long id, String owner, BigDecimal amount);
    void withdrawal(Long id, BigDecimal amount);
    void penaltyFee(Long id);
    void interestRate(Long id);
}
