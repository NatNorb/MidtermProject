package com.ironhack.MidtermProject.controller.impl;

import com.ironhack.MidtermProject.controller.interfaces.ITransactionController;
import com.ironhack.MidtermProject.dao.utils.Transaction;
import com.ironhack.MidtermProject.repository.utils.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController implements ITransactionController {

    @Autowired
    TransactionRepository transactionRepository;

    @GetMapping("/transaction")
    @ResponseStatus(HttpStatus.OK)
    public List<Transaction> search(){
        return transactionRepository.findAll();
    }
}
