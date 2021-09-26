package com.ironhack.MidtermProject.dao.additional;


import com.ironhack.MidtermProject.enums.Operations;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transId;
    @Enumerated(EnumType.STRING)
    private Operations operations;
    private long accId;
    private BigDecimal value;
    private long accHolderId;
    private LocalDateTime timestamp;
    private String foreignAccId;
    private String foreignAccHolderId;
    private boolean internalOp;

    public Transaction(Operations operations, long accId, BigDecimal value, long accHolderId,
                     String foreignAccId, String foreignAccHolderId, boolean internalOp) {
        this.operations = operations;
        this.accId = accId;
        this.value = value;
        this.accHolderId = accHolderId;
        this.timestamp = LocalDateTime.now();
       // this.timestamp = new Timestamp(System.currentTimeMillis());
        this.foreignAccId = foreignAccId;
        this.foreignAccHolderId = foreignAccHolderId;
        this.internalOp = internalOp;
    }

    public Transaction(long accHolderId, long accId, String foreignAccHolderId, String foreignAccId, boolean internalOp,
                       Operations operations, LocalDateTime timestamp, BigDecimal value) {
        this.operations = operations;
        this.accId = accId;
        this.value = value;
        this.accHolderId = accHolderId;
        this.timestamp = timestamp;
        this.foreignAccId = foreignAccId;
        this.foreignAccHolderId = foreignAccHolderId;
        this.internalOp = internalOp;
    }

}
