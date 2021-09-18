package com.ironhack.MidtermProject.service.interfaces;

import com.ironhack.MidtermProject.dao.accounts.CreditCard;

public interface ICreditCardService {

    CreditCard createCreditCard (CreditCard creditCard);
    void modifyCreditCardBalance(Long id, CreditCard creditCard);
    void addInterest();
}
