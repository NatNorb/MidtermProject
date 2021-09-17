package com.ironhack.MidtermProject;

import com.ironhack.MidtermProject.dao.Address;
import com.ironhack.MidtermProject.dao.Money;
import com.ironhack.MidtermProject.dao.accounts.*;
import com.ironhack.MidtermProject.dao.users.AccountHolder;
import com.ironhack.MidtermProject.enums.Status;
import com.ironhack.MidtermProject.repository.AddressRepository;
import com.ironhack.MidtermProject.repository.accounts.*;
import com.ironhack.MidtermProject.repository.users.AccountHolderRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.List;

@Component
public class Data {

   /* final CheckingRepository checkingRepository;
    final CreditCardRepository creditCardRepository;
    final SavingsRepository savingsRepository;
    final StudentCheckingRepository studentCheckingRepository;
    final AccountRepository accountRepository;

    final AccountHolderRepository accountHolderRepository;
    final AddressRepository addressRepository;

    public Data(CheckingRepository checkingRepository, CreditCardRepository creditCardRepository,
                SavingsRepository savingsRepository, StudentCheckingRepository studentCheckingRepository,
                AccountRepository accountRepository, AccountHolderRepository accountHolderRepository,
                AddressRepository addressRepository) {
        this.checkingRepository = checkingRepository;
        this.creditCardRepository = creditCardRepository;
        this.savingsRepository = savingsRepository;
        this.studentCheckingRepository = studentCheckingRepository;
        this.accountRepository = accountRepository;
        this.accountHolderRepository = accountHolderRepository;
        this.addressRepository = addressRepository;
    }

    List<Checking> checkingList;
    List<CreditCard> creditCardList;
    List<Savings> savingsList;
    List<StudentChecking> studentCheckingList;
    List<Account> accountList;

    List<AccountHolder> accountHolderList;
    List<Address> addressList;

    public void populateRepos(){

        addressList = addressRepository.saveAll(List.of(
                new Address("Gdansk","Dlugi Rynek", "5/2", "12-345" ),
                new Address("Poznan","Jezycka", "45", "60-865" ),
                new Address("Koscierzyna","Rynek", "10", "98-145" )
        ));

        accountHolderList = accountHolderRepository.saveAll(List.of(
                new AccountHolder("Natalia", LocalDate.of(2021,5,12), "natalia@natalia.com",addressList.get(2) ),
                new AccountHolder("Piotr", LocalDate.of(1998,8,31), "piotr@piotr.com", addressList.get(1))
        ));
        checkingList = checkingRepository.saveAll(List.of(
                new Checking(1000, "abc", "Natalia", "Piotr", null,  Status.ACTIVE, accountHolderList.get(1), 100, 12 )
        ));
        savingsList = savingsRepository.saveAll(List.of(
                new Savings(2000, "bcd", "Piotr", null, LocalDate.of(2001,5,9), Status.ACTIVE, accountHolderList.get(2), 200, 15)
        ));
    }


*/
}
