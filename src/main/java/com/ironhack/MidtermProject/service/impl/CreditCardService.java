package com.ironhack.MidtermProject.service.impl;

import com.ironhack.MidtermProject.dao.accounts.Account;
import com.ironhack.MidtermProject.dao.accounts.Checking;
import com.ironhack.MidtermProject.dao.accounts.CreditCard;
import com.ironhack.MidtermProject.dao.accounts.Savings;
import com.ironhack.MidtermProject.repository.accounts.AccountRepository;
import com.ironhack.MidtermProject.repository.accounts.CreditCardRepository;
import com.ironhack.MidtermProject.repository.accounts.SavingsRepository;
import com.ironhack.MidtermProject.service.interfaces.ICreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class CreditCardService implements ICreditCardService {

    @Autowired
    public CreditCardRepository creditCardRepository;

    @Autowired
    public AccountRepository accountRepository;

    public CreditCard createCreditCard(CreditCard creditCard){
        Optional<CreditCard> cc = creditCardRepository.findById(creditCard.getId());
        if(cc.isEmpty()){
            try{
                CreditCard newCreditCard = new CreditCard(creditCard.getBalance(),/* creditCard.getSecretKey(),  */                              creditCard.getPrimaryOwner(), creditCard.getSecondaryOwner(),
                        creditCard.getAccountHolder(), creditCard.getCreditLimit(), creditCard.getInterestRate());
                return creditCardRepository.save(newCreditCard);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Department and / or status values not valid.");
            }

        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The account id already exists in the system.");
        }
    }

    public void modifyCreditCardBalance(Long id, CreditCard creditCard){
        Optional<CreditCard> cc = creditCardRepository.findById(id);
        if (cc.isPresent()){
            cc.get().setBalance(creditCard.getBalance());
            creditCardRepository.save(cc.get());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The account id doesn't exist.");
        }
    }

    public void addInterest(){

    }
}
