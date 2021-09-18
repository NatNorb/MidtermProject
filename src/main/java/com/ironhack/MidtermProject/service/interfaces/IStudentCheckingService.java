package com.ironhack.MidtermProject.service.interfaces;

import com.ironhack.MidtermProject.dao.accounts.Checking;
import com.ironhack.MidtermProject.dao.accounts.StudentChecking;

public interface IStudentCheckingService {

    void modifyStudentCheckingBalance(Long id, StudentChecking studentChecking);
}
