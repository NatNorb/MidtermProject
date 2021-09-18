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
import java.math.BigDecimal;
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

    @GetMapping("/account")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> search(){
        return accountRepository.findAll();
    }


    @PutMapping("/account/{fromAcc}/transfer/{toAcc}/{owner}/{amount}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void transferMoney(@PathVariable Long fromAcc, @PathVariable Long toAcc,
                              @PathVariable String owner, @PathVariable BigDecimal amount){
        accountService.deposit(toAcc, owner, amount);
        accountService.withdrawal(fromAcc, amount);

    }

    @PutMapping("/penaltyFee")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void penaltyFee(){
        accountService.penaltyFee();
    }
}
