package com.ironhack.MidtermProject.controller.impl;

import com.ironhack.MidtermProject.controller.interfaces.IAccountController;
import com.ironhack.MidtermProject.dao.accounts.Account;
import com.ironhack.MidtermProject.dao.accounts.Checking;
import com.ironhack.MidtermProject.dao.accounts.CreditCard;
import com.ironhack.MidtermProject.dao.accounts.Savings;
import com.ironhack.MidtermProject.repository.accounts.AccountRepository;
import com.ironhack.MidtermProject.repository.accounts.CheckingRepository;
import com.ironhack.MidtermProject.repository.accounts.CreditCardRepository;
import com.ironhack.MidtermProject.repository.accounts.SavingsRepository;
import com.ironhack.MidtermProject.service.impl.CheckingService;
import com.ironhack.MidtermProject.service.interfaces.IAccountService;
import com.ironhack.MidtermProject.service.interfaces.ICheckingService;
import com.ironhack.MidtermProject.service.interfaces.ICreditCardService;
import com.ironhack.MidtermProject.service.interfaces.ISavingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AccountController implements IAccountController {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CheckingRepository checkingRepository;
    @Autowired
    private SavingsRepository savingsRepository;
    @Autowired
    private CreditCardRepository creditCardRepository;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private ICheckingService checkingService;
    @Autowired
    private ISavingsService savingsService;
    @Autowired
    private ICreditCardService creditCardService;


//    @GetMapping("/hello-world") //this manages a specific route
//    @ResponseStatus(HttpStatus.OK) //this is managing the status code
//    public String helloWorld(){
//        return "Hello world!! Welcome Rest API";
//    }


    @GetMapping("/account")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> search(){
        return accountRepository.findAll();
    }

    @GetMapping("/checking")
    @ResponseStatus(HttpStatus.OK)
    public List<Checking> searchChecking(){
        return checkingRepository.findAll();
  }

    @GetMapping("/saving")
    @ResponseStatus(HttpStatus.OK)
    public List<Savings> searchSaving() {
        return savingsRepository.findAll();
    }

    @GetMapping("/creditcard")
    @ResponseStatus(HttpStatus.OK)
    public List<CreditCard> searchCreditCard() {
        return creditCardRepository.findAll();
    }

    //Admin can create new account - create Checking, Savings, CreditCard
    @PostMapping("/checking")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createChecking(@RequestBody @Valid Checking checking){
        return checkingService.createChecking(checking);
    }

    @PostMapping("/savings")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createSaving(@RequestBody @Valid Savings savings){
        return savingsService.createSavings(savings);
    }

    @PostMapping("/creditcard")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createCreditCard(@RequestBody @Valid CreditCard creditCard){
        return creditCardService.createCreditCard(creditCard);
    }
}
