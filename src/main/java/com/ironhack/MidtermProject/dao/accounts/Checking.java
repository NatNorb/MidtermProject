package com.ironhack.MidtermProject.dao.accounts;

import com.ironhack.MidtermProject.dao.additional.Money;
import com.ironhack.MidtermProject.dao.users.AccountHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
//@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("checking")
public class Checking extends Account{

    private final BigDecimal minimumBalance = new BigDecimal("250");
    private final BigDecimal monthlyMaintenanceFee = new BigDecimal("12");


    public Checking(Money balance, String primaryOwner, String secondaryOwner, String secretKey, AccountHolder accountHolder) {
        super(balance, primaryOwner, secondaryOwner, secretKey,accountHolder);
    }

    public Checking(Money balance, String primaryOwner, String secondaryOwner, String secretKey, LocalDate creationDate, AccountHolder accountHolder) {
        super(balance, primaryOwner, secondaryOwner, secretKey, creationDate, accountHolder);
    }

}
