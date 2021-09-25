package com.ironhack.MidtermProject.dao.accounts;

import com.ironhack.MidtermProject.dao.additional.Money;
import com.ironhack.MidtermProject.dao.users.AccountHolder;
import com.ironhack.MidtermProject.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

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


    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "balance"))
    })
    @Embedded
    private Money balance;

    private String secretKey;
    private String primaryOwner;
    private String secondaryOwner;
    private final BigDecimal PENALTY_FEE = new BigDecimal("40");
    private LocalDate creationDate;


    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "acc_holder_id")
    private AccountHolder accountHolder;


    public Account(Money balance, String primaryOwner,
                   String secondaryOwner, String secretKey, AccountHolder accountHolder) {
        this.balance = balance;
        this.secretKey = secretKey;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.creationDate = LocalDate.now();
        this.status = Status.ACTIVE;
        this.accountHolder = accountHolder;
    }


}
