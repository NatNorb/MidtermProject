package com.ironhack.MidtermProject.service.interfaces;

import com.ironhack.MidtermProject.dao.accounts.Account;
import com.ironhack.MidtermProject.dao.accounts.Checking;

public interface ICheckingService {

    Account createChecking(Checking checking);
    void modifyCheckingBalance(Long id, Checking checking);
}
