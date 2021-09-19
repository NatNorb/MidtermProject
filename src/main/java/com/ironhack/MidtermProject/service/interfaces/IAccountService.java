package com.ironhack.MidtermProject.service.interfaces;

import java.math.BigDecimal;

public interface IAccountService {

    void deposit(Long id, String owner, BigDecimal amount);
    void withdrawal(Long id, BigDecimal amount);
    void penaltyFee(Long id);
    void interestRate(Long id);
}
