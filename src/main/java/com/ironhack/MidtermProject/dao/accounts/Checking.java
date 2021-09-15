package com.ironhack.MidtermProject.dao.accounts;

import com.ironhack.MidtermProject.dao.users.AccountHolder;
import com.ironhack.MidtermProject.enums.Status;
import com.sun.xml.bind.v2.TODO;
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
@DiscriminatorValue("checking")
public class Checking extends Account{

    private int minimumBalance;
    private int monthlyMaintenanceFee;



//TODO: 12.09.2021   When creating a new Checking account, if the primaryOwner is less than 24,
    // a StudentChecking account should be created otherwise a regular Checking Account should be created.


    public void setMinimumBalance(int minimumBalance) {
        this.minimumBalance = 250;
    }

    public void setMonthlyMaintenanceFee(int monthlyMaintenanceFee) {
        this.monthlyMaintenanceFee = 12;
    }

    // TODO: 12.09.2021 If any account drops below the minimumBalance,
    //  the penaltyFee should be deducted from the balance automatically
}
