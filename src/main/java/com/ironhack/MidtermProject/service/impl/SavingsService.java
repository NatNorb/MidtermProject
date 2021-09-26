package com.ironhack.MidtermProject.service.impl;

import com.ironhack.MidtermProject.dao.accounts.Account;
import com.ironhack.MidtermProject.dao.accounts.Checking;
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

            Savings newSavings = new Savings(savings.getBalance(), savings.getPrimaryOwner(),
                        savings.getSecondaryOwner(), savings.getSecretKey(), savings.getAccountHolder(),

                    savings.getMinimumBalance(), savings.getInterestRate());
                return savingsRepository.save(newSavings);

    }

    public void modifySavingsBalance(Long id, Savings savings){
        Optional<Savings> s = savingsRepository.findById(id);
        if (s.isPresent()){
            s.get().setBalance(savings.getBalance());
            savingsRepository.save(s.get());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The account id doesn't exist.");
        }
    }

}
