package com.ironhack.MidtermProject.service.impl;

import com.ironhack.MidtermProject.dao.accounts.Checking;
import com.ironhack.MidtermProject.dao.accounts.StudentChecking;
import com.ironhack.MidtermProject.repository.accounts.StudentCheckingRepository;
import com.ironhack.MidtermProject.service.interfaces.IStudentCheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class StudentCheckingService implements IStudentCheckingService {

    @Autowired
    private StudentCheckingRepository studentCheckingRepository;

    public void modifyStudentCheckingBalance(Long id, StudentChecking studentChecking){
        Optional<StudentChecking> sch = studentCheckingRepository.findById(id);
        if (sch.isPresent()){
            sch.get().setBalance(studentChecking.getBalance());
            studentCheckingRepository.save(sch.get());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The account id doesn't exist.");
        }
    }
}
