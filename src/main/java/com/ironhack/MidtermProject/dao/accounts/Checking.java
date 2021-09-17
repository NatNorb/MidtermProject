package com.ironhack.MidtermProject.dao.accounts;

import com.ironhack.MidtermProject.dao.Money;
import com.ironhack.MidtermProject.dao.users.AccountHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Optional;

@Entity
//@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("checking")
public class Checking extends Account{

    private final int minimumBalance = 250;
    private final int monthlyMaintenanceFee = 12;

    public Checking(BigDecimal balance, String primaryOwner, String secondaryOwner, AccountHolder accountHolder) {
        super(balance, primaryOwner, secondaryOwner, accountHolder);
    }

    //TODO: 12.09.2021   When creating a new Checking account, if the primaryOwner is less than 24,
    // a StudentChecking account should be created otherwise a regular Checking Account should be created.


    // TODO: 12.09.2021 If any account drops below the minimumBalance,
    //  the penaltyFee should be deducted from the balance automatically
}
