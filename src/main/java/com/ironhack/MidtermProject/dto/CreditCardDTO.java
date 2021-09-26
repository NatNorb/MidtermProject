package com.ironhack.MidtermProject.dto;

import com.ironhack.MidtermProject.dao.additional.Money;
import com.ironhack.MidtermProject.dao.users.AccountHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardDTO {

    private Long id;
    private Money balance;
    private String primaryOwner;
    private String secondaryOwner;
    private String secretKey;
    private Long accHolderId;
    //private int creditLimit;
    //private BigDecimal interestRate;
}
