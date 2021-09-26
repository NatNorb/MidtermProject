package com.ironhack.MidtermProject.dto;

import com.ironhack.MidtermProject.dao.additional.Money;
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
public class StudentCheckingDTO {

    private Money balance;
    private String primaryOwner;
    private String secretKey;
    private Long accHolderId;

}
