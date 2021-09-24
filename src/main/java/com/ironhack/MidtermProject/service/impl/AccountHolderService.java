package com.ironhack.MidtermProject.service.impl;

import com.ironhack.MidtermProject.dao.accounts.Checking;
import com.ironhack.MidtermProject.dao.users.AccountHolder;
import com.ironhack.MidtermProject.dao.users.ThirdParty;
import com.ironhack.MidtermProject.dao.utils.Address;
import com.ironhack.MidtermProject.repository.users.AccountHolderRepository;
import com.ironhack.MidtermProject.repository.utils.AddressRepository;
import com.ironhack.MidtermProject.service.interfaces.IAccountHolderService;
import com.ironhack.MidtermProject.service.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AccountHolderService implements IAccountHolderService {

    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    AddressRepository addressRepository;

    public AccountHolder createAccountHolder(AccountHolder accountHolder){
        Optional<AccountHolder> ah = accountHolderRepository.findByNameAndDateOfBirthAndMailingAddress(accountHolder.getName(), accountHolder.getDateOfBirth(), accountHolder.getMailingAddress());

        if(ah.isEmpty()){
            AccountHolder newAC = new AccountHolder(accountHolder.getName(), accountHolder.getDateOfBirth(),
                    accountHolder.getMailingAddress(), accountHolder.getPrimaryAddress());
            return accountHolderRepository.save(newAC);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "The Account Holder named " + accountHolder.getName() +
                            " with e-mail address " + accountHolder.getMailingAddress() +
                            " and date of birth " + accountHolder.getDateOfBirth() +
                            " already exists in the system.");
        }
    }

    public void modifyAccountHolder(Long id, AccountHolder accountHolder){
        Optional<AccountHolder> ah = accountHolderRepository.findById(id);
        if(ah.isPresent()){
            ah.get().setPrimaryAddress(accountHolder.getPrimaryAddress());
            accountHolderRepository.save(ah.get());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no account holder.");
        }
    }
}
