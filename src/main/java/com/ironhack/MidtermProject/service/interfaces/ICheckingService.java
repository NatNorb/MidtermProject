package com.ironhack.MidtermProject.service.interfaces;

import com.ironhack.MidtermProject.dao.accounts.Checking;

public interface ICheckingService {

    Checking createChecking(Checking checking);
    void modifyCheckingBalance(Long id, Checking checking);
}
