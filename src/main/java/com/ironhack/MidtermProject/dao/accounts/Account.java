package com.ironhack.MidtermProject.dao.accounts;

import com.ironhack.MidtermProject.dao.Money;
import com.ironhack.MidtermProject.dao.users.AccountHolder;
import com.ironhack.MidtermProject.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="acc_type")
//@MappedSuperclass
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double balance;
    private String secretKey;
    private String primaryOwner;
    private String secondaryOwner;
    private final int PENALTY_FEE = 40;
    private LocalDate creationDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "acc_holder_id")
    private AccountHolder accountHolder;

    public Account(double balance, String secretKey, String primaryOwner,
                   String secondaryOwner, AccountHolder accountHolder) {
        this.balance = balance;
        this.secretKey = secretKey; //UUID.randomUUID();
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.creationDate = LocalDate.now();
        this.status = Status.ACTIVE;
        this.accountHolder = accountHolder;
    }


}
