package com.ironhack.MidtermProject.dao.accounts;

import com.ironhack.MidtermProject.dao.Money;
import com.ironhack.MidtermProject.dao.users.AccountHolder;
import com.ironhack.MidtermProject.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Entity
//@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("saving")
public class Savings extends Account{

    private int minimumBalance;
    private double interestRate;


    public Savings(BigDecimal balance, String primaryOwner, String secondaryOwner, AccountHolder accountHolder,
                   int minimumBalance, double interestRate) {
        super(balance, primaryOwner, secondaryOwner, accountHolder);
        setMinimumBalance(minimumBalance);
        setInterestRate(interestRate);
    }

    public void setInterestRate(double interestRate){
        if (interestRate <= 0.0 || interestRate > 0.5){
            this.interestRate = 0.0025;
        } else {this.interestRate = interestRate;
        }
    }

    public void setMinimumBalance(int minimumBalance) {
        if (minimumBalance < 100){
            this.minimumBalance = 1000;
        } else {
            this.minimumBalance = minimumBalance;
        }
    }

    // TODO: 12.09.2021 If any account drops below the minimumBalance,
    //  the penaltyFee should be deducted from the balance automatically
}
