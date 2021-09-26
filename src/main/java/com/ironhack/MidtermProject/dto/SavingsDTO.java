package com.ironhack.MidtermProject.dto;

import com.ironhack.MidtermProject.dao.additional.Money;
import com.ironhack.MidtermProject.dao.users.AccountHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SavingsDTO {

    private Long id;
    private Money balance;
    private String primaryOwner;
    private String secretKey;
    private Long accHolderId;
    private BigDecimal minimumBalance;
    //@Digits(integer = 1, fraction = 4)
    //private BigDecimal interestRate;



}
