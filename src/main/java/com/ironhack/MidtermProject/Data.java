package com.ironhack.MidtermProject;

import com.ironhack.MidtermProject.dao.accounts.*;
import com.ironhack.MidtermProject.dao.users.AccountHolder;
import com.ironhack.MidtermProject.dao.utils.Address;
import com.ironhack.MidtermProject.dao.utils.Money;
import com.ironhack.MidtermProject.enums.Status;
import com.ironhack.MidtermProject.repository.accounts.*;
import com.ironhack.MidtermProject.repository.users.AccountHolderRepository;
import com.ironhack.MidtermProject.repository.utils.AddressRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class Data {

    final CheckingRepository checkingRepository;
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
                new Address("Shire","Shire", "8", "12-345"),
                new Address("Gondor", "Mordor St", "45", "456-789"),
                new Address("Rohan", "Castle","12/100", "159-753")
        ));

        accountHolderList = accountHolderRepository.saveAll(List.of(
                new AccountHolder("Bilbo",LocalDate.of(1975,9,21), "bilbo@baggins.com",  addressList.get(1)),
                new AccountHolder("Frodo",LocalDate.of(2000,12,31), "frodo@baggins.com",  addressList.get(1)),
                new AccountHolder("Aragorn",LocalDate.of(1979,02,7), "aragorn@king.com",  addressList.get(2)),
                new AccountHolder("Eowina",LocalDate.of(1985,10,4), "eowina@rohan.com",  addressList.get(3))
        ));

        checkingList = checkingRepository.saveAll(List.of(
                new Checking(new Money(new BigDecimal(12000)), "Bilbo", "Frodo", "DEF", accountHolderList.get(1))
        ));

        savingsList = savingsRepository.saveAll(List.of(
                new Savings(new Money(new BigDecimal(5000)), "Aragorn", null, "ABC", accountHolderList.get(3), new BigDecimal("0"), new BigDecimal("0")),
                new Savings(new Money(new BigDecimal(1000)), "Bilbo", null, "BCD",  accountHolderList.get(1), new BigDecimal("110"), new BigDecimal("0.25"))
        ));

        creditCardList = creditCardRepository.saveAll(List.of(
                new CreditCard(new Money( new BigDecimal(1200)), "Eowina", null, "CDE", accountHolderList.get(4), 100, new BigDecimal("0.2"))
        ));

        System.out.println("DATA SUCCESSFULLY IMPLEMENTED");
    }

}
