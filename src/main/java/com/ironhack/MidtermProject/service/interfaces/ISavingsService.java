package com.ironhack.MidtermProject.service.interfaces;

import com.ironhack.MidtermProject.dao.accounts.Savings;

public interface ISavingsService {

    Savings createSavings(Savings savings);
    void modifySavingsBalance(Long id, Savings savings);
}
