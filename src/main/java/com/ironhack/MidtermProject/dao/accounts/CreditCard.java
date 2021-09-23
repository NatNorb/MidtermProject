package com.ironhack.MidtermProject.dao.accounts;

import com.ironhack.MidtermProject.dao.utils.Money;
import com.ironhack.MidtermProject.dao.users.AccountHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("credit_card")
public class CreditCard extends Account{

    private int creditLimit;

    @Digits(integer = 1, fraction = 4)
    private BigDecimal interestRate;

    public CreditCard(Money balance, String primaryOwner, String secondaryOwner, String secretKey, AccountHolder accountHolder,
                      int creditLimit, BigDecimal interestRate) {
        super(balance, primaryOwner, secondaryOwner, secretKey, accountHolder);
        setCreditLimit(creditLimit);
        setInterestRate(interestRate);
    }

    public void setCreditLimit(int creditLimit) {
        if (creditLimit <= 0 || creditLimit > 100000){
            this.creditLimit = 100;
        } else {
            this.creditLimit = creditLimit;
        }
    }

    public void setInterestRate(BigDecimal interestRate){
        if (interestRate.compareTo(new BigDecimal("0.1")) <= 0 || interestRate.compareTo(new BigDecimal("0.2")) == 1){
            this.interestRate = new BigDecimal("0.2");
        } else {this.interestRate = interestRate;
        }
    }
}
