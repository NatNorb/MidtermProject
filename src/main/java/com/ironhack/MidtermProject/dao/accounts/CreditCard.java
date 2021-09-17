package com.ironhack.MidtermProject.dao.accounts;

import com.ironhack.MidtermProject.dao.Money;
import com.ironhack.MidtermProject.dao.users.AccountHolder;
import com.ironhack.MidtermProject.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Optional;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("credit_card")
public class CreditCard extends Account{

    private int creditLimit;
    private double interestRate;

    public CreditCard(double balance, String secretKey, String primaryOwner, String secondaryOwner, AccountHolder accountHolder, int creditLimit, double interestRate) {
        super(balance, secretKey, primaryOwner, secondaryOwner, accountHolder);
        setCreditLimit(creditLimit);
        setInterestRate(interestRate);
    }

    public void setCreditLimit(int creditLimit) {
        if (creditLimit <= 0 || creditLimit > 100000){
            this.creditLimit = 100;
        } this.creditLimit = creditLimit;
    }

    public void setInterestRate(double interestRate){
        if (interestRate < 0.1){
            this.interestRate = 0.2;
        } this.interestRate = interestRate;
    }
}
