package com.ironhack.MidtermProject.dao.accounts;

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
@Getter
@Setter
@DiscriminatorValue("student")
public class StudentChecking extends Account {

    public StudentChecking(BigDecimal balance, String primaryOwner, String secondaryOwner, AccountHolder accountHolder) {
        super(balance, primaryOwner, secondaryOwner, accountHolder);
    }
}

