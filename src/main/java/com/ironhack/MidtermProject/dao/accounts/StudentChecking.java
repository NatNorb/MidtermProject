package com.ironhack.MidtermProject.dao.accounts;

import com.ironhack.MidtermProject.dao.additional.Money;
import com.ironhack.MidtermProject.dao.users.AccountHolder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("student")
public class StudentChecking extends Account {


    public StudentChecking(Money balance, String primaryOwner, String secondaryOwner, String secretKey, AccountHolder accountHolder) {
        super(balance, primaryOwner, secondaryOwner, secretKey, accountHolder);
    }

    public StudentChecking(Money balance, String primaryOwner, String secondaryOwner, String secretKey, LocalDate creationDate, AccountHolder accountHolder) {
        super(balance, primaryOwner, secondaryOwner, secretKey, creationDate, accountHolder);
    }
}

