package com.ironhack.MidtermProject.service.impl;

import com.ironhack.MidtermProject.dao.Money;
import com.ironhack.MidtermProject.dao.accounts.Account;
import com.ironhack.MidtermProject.dao.accounts.Checking;
import com.ironhack.MidtermProject.dao.accounts.StudentChecking;
import com.ironhack.MidtermProject.dao.users.AccountHolder;
import com.ironhack.MidtermProject.repository.accounts.AccountRepository;
import com.ironhack.MidtermProject.repository.accounts.CheckingRepository;
import com.ironhack.MidtermProject.repository.accounts.StudentCheckingRepository;
import com.ironhack.MidtermProject.repository.users.AccountHolderRepository;
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
    @Autowired
    public CheckingRepository checkingRepository;
    @Autowired
    public StudentCheckingRepository studentCheckingRepository;
    @Autowired
    public AccountHolderRepository accountHolderRepository;

    public Account create(Checking checking) {
        Optional<Checking> ch = checkingRepository.findById(checking.getId());

        Optional<AccountHolder> accountHolder = accountHolderRepository.findById(checking.getAccountHolder().getAccHolderId());

        if (ch.isEmpty()){
            if (accountHolder.get().howOld() > 24){
                Checking newChecking = new Checking(checking.getBalance(), checking.getPrimaryOwner(),
                        checking.getSecondaryOwner(), checking.getAccountHolder());
                return checkingRepository.save(newChecking);
            } else{
                StudentChecking newStudentChecking = new StudentChecking(checking.getBalance(), checking.getPrimaryOwner(),
                        checking.getSecondaryOwner(), checking.getAccountHolder());
                return studentCheckingRepository.save(newStudentChecking);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The account id already exists in the system.");
        }

    }


    public void deposit(Long id, String owner, BigDecimal amount){
        Optional<Account> accDeposit = accountRepository.findById(id);
        if (accDeposit.isPresent()){
            Money initBal = accDeposit.get().getBalance();
            Money finalBal = new Money(initBal.increaseAmount(amount));
            accDeposit.get().setBalance(finalBal);
            accountRepository.save(accDeposit.get());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The account id does not exists in the system.");
        }

    }
    public void withdrawal(Long id, BigDecimal amount){
        Optional<Account> accWithdrawal = accountRepository.findById(id);
        if (accWithdrawal.isPresent()){
            Money initBal = accWithdrawal.get().getBalance();
            Money finalBal = new Money(initBal.decreaseAmount(amount));
            if (finalBal.getAmount().compareTo(BigDecimal.ZERO) > 0){
                accWithdrawal.get().setBalance(finalBal);
                accountRepository.save(accWithdrawal.get());
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is not enough resources");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The account id does not exists in the system.");
        }
    }

    public void penaltyFee(){

    }


}
