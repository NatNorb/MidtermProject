package com.ironhack.MidtermProject.service.impl;

import com.ironhack.MidtermProject.dao.Money;
import com.ironhack.MidtermProject.dao.Transaction;
import com.ironhack.MidtermProject.dao.accounts.*;
import com.ironhack.MidtermProject.dao.users.AccountHolder;
import com.ironhack.MidtermProject.enums.Operations;
import com.ironhack.MidtermProject.repository.TransactionRepository;
import com.ironhack.MidtermProject.repository.accounts.*;
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
    AccountRepository accountRepository;
    @Autowired
    CheckingRepository checkingRepository;
    @Autowired
    SavingsRepository savingsRepository;
    @Autowired
    CreditCardRepository creditCardRepository;
    @Autowired
    StudentCheckingRepository studentCheckingRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    TransactionRepository transactionRepository;

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
            Transaction newTransaction = new Transaction(Operations.DEPOSIT, accDeposit.get().getId(), amount, accDeposit.get().getAccountHolder().getAccHolderId());
            transactionRepository.save(newTransaction);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The account id does not exists in the system.");
        }

    }
    public void withdrawal(Long id, BigDecimal amount){
        Optional<Account> accWithdrawal = accountRepository.findById(id);
        if (accWithdrawal.isPresent()){
            Money initBal = accWithdrawal.get().getBalance();
            Money finalBal = new Money(initBal.decreaseAmount(amount));
            if (finalBal.getAmount().compareTo(BigDecimal.ZERO) >= 0){
                accWithdrawal.get().setBalance(finalBal);
                accountRepository.save(accWithdrawal.get());
                Transaction newTransaction = new Transaction(Operations.WITHDRAWAL, accWithdrawal.get().getId(), amount.multiply(new BigDecimal("-1")), accWithdrawal.get().getAccountHolder().getAccHolderId());
                transactionRepository.save(newTransaction);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is not enough resources");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The account id does not exists in the system.");
        }
    }

    public void penaltyFee(Long id){
        Optional<Savings> s = savingsRepository.findById(id);
        Optional<Checking> c = checkingRepository.findById(id);
        if (s.isPresent()){
           if (s.get().getBalance().getAmount().compareTo(s.get().getMinimumBalance()) == -1){
               BigDecimal newBalanceBD = s.get().getBalance().getAmount().subtract(s.get().getPENALTY_FEE());
               Money newBalance = new Money(newBalanceBD);
               s.get().setBalance(newBalance);
               savingsRepository.save(s.get());
               Transaction newTransaction = new Transaction(Operations.PENALTY_FEE, s.get().getId(), newBalanceBD.multiply(new BigDecimal("-1")), s.get().getAccountHolder().getAccHolderId());
               transactionRepository.save(newTransaction);
            }
        } else if (c.isPresent()){
            if (c.get().getBalance().getAmount().compareTo(c.get().getMinimumBalance()) == -1){
                BigDecimal newBalanceBD = c.get().getBalance().getAmount().subtract(c.get().getPENALTY_FEE());
                Money newBalance = new Money(newBalanceBD);
                c.get().setBalance(newBalance);
                checkingRepository.save(c.get());
                Transaction newTransaction = new Transaction(Operations.PENALTY_FEE, c.get().getId(), newBalanceBD.multiply(new BigDecimal("-1")), c.get().getAccountHolder().getAccHolderId());
                transactionRepository.save(newTransaction);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The account id does not exists in the system.");
        }

    }

    public void interestRate(Long id){
//        Optional<Savings> s = savingsRepository.findById(id);
//        Optional<CreditCard> cc = creditCardRepository.findById(id);
//        if (s.isPresent()){
//            if
//
//
//        } else if (cc.isPresent()){
//
//
//
//        } else {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The account id does not exists in the system.");
  //      }

    }


}
