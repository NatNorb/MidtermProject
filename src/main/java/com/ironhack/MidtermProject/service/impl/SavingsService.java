package com.ironhack.MidtermProject.service.impl;

import com.ironhack.MidtermProject.dao.accounts.Account;
import com.ironhack.MidtermProject.dao.accounts.Savings;
import com.ironhack.MidtermProject.repository.accounts.AccountRepository;
import com.ironhack.MidtermProject.repository.accounts.SavingsRepository;
import com.ironhack.MidtermProject.service.interfaces.ISavingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class SavingsService implements ISavingsService {

    @Autowired
    public SavingsRepository savingsRepository;

    @Autowired
    public AccountRepository accountRepository;

    public Savings createSavings(Savings savings){
        Optional<Savings> s = savingsRepository.findById(savings.getId());
        if(s.isEmpty()){
            try{
                Savings newSavings = new Savings(savings.getBalance(), savings.getPrimaryOwner(),
                        savings.getSecondaryOwner(), savings.getAccountHolder(),
                        savings.getMinimumBalance(), savings.getInterestRate());
                return savingsRepository.save(newSavings);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Department and / or status values not valid.");
            }

        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The account id already exists in the system.");
        }
    }
}
