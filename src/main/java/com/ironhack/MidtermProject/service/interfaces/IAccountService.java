package com.ironhack.MidtermProject.service.interfaces;

import com.ironhack.MidtermProject.enums.Operations;

import java.math.BigDecimal;

public interface IAccountService {

    void transaction(Long fromAcc, BigDecimal amount, Long toAcc);
    void penaltyFee(Long id);
    void interestRate(Long id);
    void transactionThirdParty(Long accId, String secretKey, BigDecimal amount, String hashedKey, Operations operations);
    void fraudDetectionV1();
    void fraudDetectionV2();
    void unfreeze(Long id);

}
