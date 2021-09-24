package com.ironhack.MidtermProject.controller.impl;

import com.ironhack.MidtermProject.controller.interfaces.IAdminController;
import com.ironhack.MidtermProject.dao.accounts.Account;
import com.ironhack.MidtermProject.dao.accounts.Checking;
import com.ironhack.MidtermProject.dao.users.AccountHolder;
import com.ironhack.MidtermProject.dao.users.Admin;
import com.ironhack.MidtermProject.dao.users.ThirdParty;
import com.ironhack.MidtermProject.dao.utils.Address;
import com.ironhack.MidtermProject.repository.accounts.AccountRepository;
import com.ironhack.MidtermProject.repository.users.AccountHolderRepository;
import com.ironhack.MidtermProject.repository.users.AdminRepository;
import com.ironhack.MidtermProject.repository.users.ThirdPartyRepository;
import com.ironhack.MidtermProject.repository.utils.AddressRepository;
import com.ironhack.MidtermProject.service.impl.AccountHolderService;
import com.ironhack.MidtermProject.service.impl.AccountService;
import com.ironhack.MidtermProject.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController implements IAdminController {

    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    ThirdPartyRepository thirdPartyRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    IAccountHolderService accountHolderService;
    @Autowired
    IAdminService adminService;
    @Autowired
    IThirdPartyService thirdPartyService;
    @Autowired
    IAddressService addressService;
    @Autowired
    IAccountService accountService;


    @GetMapping("/account-holder")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountHolder> searchAccountHolders(){
        return accountHolderRepository.findAll();
    }

    @GetMapping("/third-party")
    @ResponseStatus(HttpStatus.OK)
    public List<ThirdParty> searchThirdParty(){
        return thirdPartyRepository.findAll();
    }

    @GetMapping("/address")
    @ResponseStatus(HttpStatus.OK)
    public List<Address> searchAddress(){
        return addressRepository.findAll();
    }

    @PostMapping("/add/account-holder")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolder createAccountHolder(@RequestBody @Valid AccountHolder accountHolder){
        return accountHolderService.createAccountHolder(accountHolder);
    }

    @PutMapping("/add/account-holder/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAccountHolder(@PathVariable Long id,@RequestBody @Valid AccountHolder accountHolder){
        accountHolderService.modifyAccountHolder(id, accountHolder);
    }

    @PostMapping("/add/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public Admin createAdmin(@RequestBody @Valid Admin admin){
        return adminService.createAdmin(admin);
    }

    @PostMapping("/add/third-party")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty createThirdParty(@RequestBody @Valid ThirdParty thirdParty){
        return thirdPartyService.createThirdParty(thirdParty);
    }

    @PostMapping("/add/address")
    @ResponseStatus(HttpStatus.CREATED)
    public Address createAddress(@RequestBody @Valid Address address){
        return addressService.createAddress(address);
    }

    @PutMapping("/unfreeze/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unfreeze(@PathVariable Long id){
        accountService.unfreeze(id);
    }


}
