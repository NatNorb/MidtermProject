package com.ironhack.MidtermProject.controller.impl;

import com.ironhack.MidtermProject.controller.interfaces.IAccountController;
import com.ironhack.MidtermProject.dao.accounts.Account;
import com.ironhack.MidtermProject.enums.Operations;
import com.ironhack.MidtermProject.repository.accounts.AccountRepository;
import com.ironhack.MidtermProject.repository.accounts.CheckingRepository;
import com.ironhack.MidtermProject.repository.accounts.CreditCardRepository;
import com.ironhack.MidtermProject.repository.accounts.SavingsRepository;
import com.ironhack.MidtermProject.repository.utils.TransactionRepository;
import com.ironhack.MidtermProject.service.interfaces.IAccountService;
import com.ironhack.MidtermProject.service.interfaces.ICheckingService;
import com.ironhack.MidtermProject.service.interfaces.ICreditCardService;
import com.ironhack.MidtermProject.service.interfaces.ISavingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class AccountController implements IAccountController {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CheckingRepository checkingRepository;
    @Autowired
    SavingsRepository savingsRepository;
    @Autowired
    CreditCardRepository creditCardRepository;
    @Autowired
    IAccountService accountService;
    @Autowired
    ICheckingService checkingService;
    @Autowired
    ISavingsService savingsService;
    @Autowired
    ICreditCardService creditCardService;
    @Autowired
    TransactionRepository transactionRepository;

    @GetMapping("/admin/account")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> search(){
        return accountRepository.findAll();
    }


    @PutMapping("/account-holder/{fromAcc}/transfer/{toAcc}/{owner}/{amount}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void transferMoney(@PathVariable Long fromAcc, @PathVariable Long toAcc,
                              @PathVariable String owner, @PathVariable BigDecimal amount){
        accountService.transaction(fromAcc, amount, toAcc);
    }

    @PutMapping("/third-party/{hashedKey}/{amount}/{accId}/{secretKey}/{operation}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void thirdPartyOperation(@PathVariable String hashedKey, @PathVariable BigDecimal amount,
                                    @PathVariable Long accId, @PathVariable String secretKey, @PathVariable Operations operation){
        accountService.transactionThirdParty(accId, secretKey, amount, hashedKey, operation);

    }

    @PutMapping("/admin/penaltyFee/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void penaltyFee(@PathVariable Long id){
        accountService.penaltyFee(id);
    }


    @PutMapping("/admin/interest/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void interestRate(@PathVariable Long id){
        accountService.interestRate(id);
    }

    @GetMapping("/account-holder/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> searchByName(@PathVariable String name){
        return accountRepository.findByPrimaryOwner(name);
    }

    @PutMapping("/admin/fraud-detection-v1")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void fraudDetectionV1(){
        accountService.fraudDetectionV1();
    }

    @PutMapping("/admin/fraud-detection-v2")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void fraudDetectionV2(){
        accountService.fraudDetectionV2();
    }




}
