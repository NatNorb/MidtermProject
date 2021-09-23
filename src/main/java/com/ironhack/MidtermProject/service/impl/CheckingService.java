package com.ironhack.MidtermProject.service.impl;

import com.ironhack.MidtermProject.dao.accounts.Account;
import com.ironhack.MidtermProject.dao.accounts.Checking;
import com.ironhack.MidtermProject.dao.accounts.StudentChecking;
import com.ironhack.MidtermProject.dao.users.AccountHolder;
import com.ironhack.MidtermProject.repository.accounts.AccountRepository;
import com.ironhack.MidtermProject.repository.accounts.CheckingRepository;
import com.ironhack.MidtermProject.repository.accounts.StudentCheckingRepository;
import com.ironhack.MidtermProject.repository.users.AccountHolderRepository;
import com.ironhack.MidtermProject.service.interfaces.ICheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Optional;

@Service
public class CheckingService implements ICheckingService {

    @Autowired
    public CheckingRepository checkingRepository;
    @Autowired
    public StudentCheckingRepository studentCheckingRepository;
    @Autowired
    public AccountHolderRepository accountHolderRepository;

    public Account createChecking(Checking checking) {
        Optional<Checking> ch = checkingRepository.findById(checking.getId());

        Optional<AccountHolder> accountHolder = accountHolderRepository.findById(checking.getAccountHolder().getAccHolderId());

        if (ch.isEmpty()){
            if (accountHolder.get().howOld() > 24){
                Checking newChecking = new Checking(checking.getBalance(), checking.getPrimaryOwner(),
                        checking.getSecondaryOwner(), checking.getSecretKey(),  checking.getAccountHolder());
                return checkingRepository.save(newChecking);
            } else{
                StudentChecking newStudentChecking = new StudentChecking(checking.getBalance(), checking.getPrimaryOwner(),
                        checking.getSecondaryOwner(), checking.getSecretKey(),  checking.getAccountHolder());
                return studentCheckingRepository.save(newStudentChecking);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The account id already exists in the system.");
        }

    }

    public void modifyCheckingBalance(Long id, Checking checking){
        Optional<Checking> ch = checkingRepository.findById(id);
        if (ch.isPresent()){
                ch.get().setBalance(checking.getBalance());
                checkingRepository.save(ch.get());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The account id doesn't exist.");
        }
    }
}


