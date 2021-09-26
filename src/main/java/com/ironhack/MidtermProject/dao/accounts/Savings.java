package com.ironhack.MidtermProject.dao.accounts;

import com.ironhack.MidtermProject.dao.additional.Money;
import com.ironhack.MidtermProject.dao.users.AccountHolder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
//@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("saving")
public class Savings extends Account{

    private BigDecimal minimumBalance;

    @Digits(integer = 1, fraction = 4)
    private BigDecimal interestRate = BigDecimal.ZERO;


    public Savings(Money balance, String primaryOwner, String secondaryOwner, String secretKey, AccountHolder accountHolder,
                   BigDecimal minimumBalance, BigDecimal interestRate) {
        super(balance, primaryOwner, secondaryOwner, secretKey, accountHolder);
        setMinimumBalance(minimumBalance);
        setInterestRate(interestRate);
    }

    public Savings(Money balance, String primaryOwner, String secondaryOwner, String secretKey, LocalDate creationDate, AccountHolder accountHolder,
                   BigDecimal minimumBalance, BigDecimal interestRate) {
        super(balance, primaryOwner, secondaryOwner, secretKey, creationDate, accountHolder);
        setMinimumBalance(minimumBalance);
        setInterestRate(interestRate);
    }

    public void setInterestRate(BigDecimal interestRate){
        if (interestRate.compareTo(new BigDecimal("0")) <= 0 || interestRate.compareTo(new BigDecimal("0.5")) == 1){
            this.interestRate = new BigDecimal("0.0025");
        } else {this.interestRate = interestRate;
        }
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        if (minimumBalance.compareTo(new BigDecimal("100")) == -1 || minimumBalance.compareTo(new BigDecimal("1000")) == 1){
            this.minimumBalance = new BigDecimal("1000");
        } else {
            this.minimumBalance = minimumBalance;
        }
    }
}
