package com.ironhack.MidtermProject.dao.accounts;

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





    public void setCreditLimit(int creditLimit) {
        if (creditLimit <= 0 || creditLimit > 100000){
            this.creditLimit = 100;
        } this.creditLimit = creditLimit;
    }

    public void SetInterestRate(double interestRate){
        if (interestRate < 0.1){
            this.interestRate = 0.2;
        } this.interestRate = interestRate;
    }
}
