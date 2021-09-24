package com.ironhack.MidtermProject.controller.impl;

import com.ironhack.MidtermProject.controller.interfaces.ISavingdController;
import com.ironhack.MidtermProject.dao.accounts.Account;
import com.ironhack.MidtermProject.dao.accounts.Checking;
import com.ironhack.MidtermProject.dao.accounts.CreditCard;
import com.ironhack.MidtermProject.dao.accounts.Savings;
import com.ironhack.MidtermProject.repository.accounts.CheckingRepository;
import com.ironhack.MidtermProject.repository.accounts.CreditCardRepository;
import com.ironhack.MidtermProject.repository.accounts.SavingsRepository;
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
@RequestMapping("/admin")
public class SavingsController implements ISavingdController {

    @Autowired
    private SavingsRepository savingsRepository;

    @Autowired
    private ISavingsService savingsService;

    @GetMapping("/savings")
    @ResponseStatus(HttpStatus.OK)
    public List<Savings> searchSaving() {
        return savingsRepository.findAll();
    }

    //Admin can create new account - create Checking, Savings, CreditCard
    @PostMapping("/savings")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createSaving(@RequestBody @Valid Savings savings){
        return savingsService.createSavings(savings);
    }

    //Admins should be able to access the balance for any account and to modify it.
    @PatchMapping("/savings/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void modifySavingsBalance(@PathVariable Long id, @RequestBody @Valid Savings savings){
        savingsService.modifySavingsBalance(id, savings);
    }


}
