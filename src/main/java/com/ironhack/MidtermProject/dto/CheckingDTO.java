package com.ironhack.MidtermProject.dto;

import com.ironhack.MidtermProject.dao.additional.Money;
import com.ironhack.MidtermProject.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckingDTO {

    private Long id;
    private Money balance;
    private String primaryOwner;
    private String secretKey;
    private Long accHolderId;
    private Status status;
}
