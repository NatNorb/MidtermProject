package com.ironhack.MidtermProject.service.impl;

import com.ironhack.MidtermProject.dao.accounts.Account;
import com.ironhack.MidtermProject.dao.accounts.Checking;
import com.ironhack.MidtermProject.dao.accounts.StudentChecking;
import com.ironhack.MidtermProject.dao.users.AccountHolder;
import com.ironhack.MidtermProject.dao.users.ThirdParty;
import com.ironhack.MidtermProject.repository.users.ThirdPartyRepository;
import com.ironhack.MidtermProject.service.interfaces.IThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ThirdPartyService implements IThirdPartyService {

    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    public ThirdParty createThirdParty(ThirdParty thirdParty){
        Optional<ThirdParty> tp = thirdPartyRepository.findByHashedKey(thirdParty.getHashedKey());
        if (tp.isEmpty()){
            ThirdParty newTP = new ThirdParty(thirdParty.getName(), thirdParty.getHashedKey());
            return thirdPartyRepository.save(newTP);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The Third-Party user with " + thirdParty.getHashedKey() + "hashedKey already exists in the system.");
        }
    }
}
