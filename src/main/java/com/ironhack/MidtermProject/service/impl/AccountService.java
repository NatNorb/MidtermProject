package com.ironhack.MidtermProject.service.impl;

import com.ironhack.MidtermProject.dao.accounts.Account;
import com.ironhack.MidtermProject.dao.accounts.Checking;
import com.ironhack.MidtermProject.repository.accounts.AccountRepository;
import com.ironhack.MidtermProject.repository.accounts.CheckingRepository;
import com.ironhack.MidtermProject.service.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {

    @Autowired
    public AccountRepository accountRepository;

    public void deposit(Long id, String owner, BigDecimal amount){
        Optional<Account> accDeposit = accountRepository.findById(id);
        if (accDeposit.isPresent()){
            BigDecimal initBal = accDeposit.get().getBalance();
            BigDecimal finalBal = initBal.add(amount);
            accDeposit.get().setBalance(finalBal);
            accountRepository.save(accDeposit.get());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The account id does not exists in the system.");
        }

    }
    public void withdrawal(Long id, BigDecimal amount){
        Optional<Account> accWithdrawal = accountRepository.findById(id);
        if (accWithdrawal.isPresent()){
            BigDecimal initBal = accWithdrawal.get().getBalance();
            BigDecimal finalBal = initBal.subtract(amount);
            if (finalBal.compareTo(BigDecimal.ZERO) > 0){
                accWithdrawal.get().setBalance(finalBal);
                accountRepository.save(accWithdrawal.get());
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is not enough resources");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The account id does not exists in the system.");
        }
    }


}
