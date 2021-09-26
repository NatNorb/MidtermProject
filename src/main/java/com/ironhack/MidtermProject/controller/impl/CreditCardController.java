package com.ironhack.MidtermProject.controller.impl;

import com.ironhack.MidtermProject.controller.interfaces.ICreditCardController;
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
public class CreditCardController implements ICreditCardController {

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private ICreditCardService creditCardService;

    @GetMapping("/credit-card")
    @ResponseStatus(HttpStatus.OK)
    public List<CreditCard> searchCreditCard() {
        return creditCardRepository.findAll();
    }

    @PostMapping("/credit-card")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createCreditCard(@RequestBody @Valid CreditCard creditCard){
        return creditCardService.createCreditCard(creditCard);
    }

    @PatchMapping("/credit-card/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void modifyCreditCardBalance(@PathVariable Long id, @RequestBody @Valid CreditCard creditCard){
        creditCardService.modifyCreditCardBalance(id, creditCard);
    }
}
