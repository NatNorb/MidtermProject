package com.ironhack.MidtermProject.dao.accounts;

import com.ironhack.MidtermProject.dao.utils.Money;
import com.ironhack.MidtermProject.dao.users.AccountHolder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("student")
public class StudentChecking extends Account {


    public StudentChecking(Money balance, String primaryOwner, String secondaryOwner, AccountHolder accountHolder) {
        super(balance, primaryOwner, secondaryOwner, accountHolder);
    }
}

