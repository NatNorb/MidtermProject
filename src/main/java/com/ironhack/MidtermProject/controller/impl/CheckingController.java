package com.ironhack.MidtermProject.controller.impl;

import com.ironhack.MidtermProject.controller.interfaces.ICheckingController;
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
public class CheckingController implements ICheckingController {

    @Autowired
    private CheckingRepository checkingRepository;

    @Autowired
    private ICheckingService checkingService;


    @GetMapping("/checking")
    @ResponseStatus(HttpStatus.OK)
    public List<Checking> searchChecking(){
        return checkingRepository.findAll();
    }

    //Admin can create new account - create Checking, Savings, CreditCard
    @PostMapping("/checking")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createChecking(@RequestBody @Valid Checking checking){
        return checkingService.createChecking(checking);
    }

    //Admins should be able to access the balance for any account and to modify it.
    @PatchMapping("/checking/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void modifyCheckingBalance(@PathVariable Long id, @RequestBody @Valid Checking checking){
        checkingService.modifyCheckingBalance(id, checking);

    }
}
