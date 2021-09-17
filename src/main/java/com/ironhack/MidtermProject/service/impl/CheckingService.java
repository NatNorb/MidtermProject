package com.ironhack.MidtermProject.service.impl;

import com.ironhack.MidtermProject.dao.accounts.Checking;
import com.ironhack.MidtermProject.dao.users.AccountHolder;
import com.ironhack.MidtermProject.repository.accounts.CheckingRepository;
import com.ironhack.MidtermProject.service.interfaces.ICheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class CheckingService implements ICheckingService {

    @Autowired
    public CheckingRepository checkingRepository;

    public Checking createChecking(Checking checking){
        Optional<Checking> ch = checkingRepository.findById(checking.getId());
        if (ch.isEmpty()){
            try{
                Checking newChecking = new Checking(checking.getBalance(), checking.getSecretKey(), checking.getPrimaryOwner(),
                        checking.getSecondaryOwner(), checking.getAccountHolder());
                return checkingRepository.save(newChecking);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Department and / or status values not valid.");
            }

        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The account id already exists in the system.");
        }
            }

}


