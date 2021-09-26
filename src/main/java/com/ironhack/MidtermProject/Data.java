package com.ironhack.MidtermProject;

import com.ironhack.MidtermProject.dao.accounts.*;
import com.ironhack.MidtermProject.dao.additional.Transaction;
import com.ironhack.MidtermProject.dao.users.AccountHolder;
import com.ironhack.MidtermProject.dao.additional.Address;
import com.ironhack.MidtermProject.dao.additional.Money;
import com.ironhack.MidtermProject.dao.users.Role;
import com.ironhack.MidtermProject.dao.users.ThirdParty;
import com.ironhack.MidtermProject.dao.users.User;
import com.ironhack.MidtermProject.enums.Operations;
import com.ironhack.MidtermProject.repository.accounts.*;
import com.ironhack.MidtermProject.repository.additional.TestsRepository;
import com.ironhack.MidtermProject.repository.additional.TransactionRepository;
import com.ironhack.MidtermProject.repository.users.AccountHolderRepository;
import com.ironhack.MidtermProject.repository.additional.AddressRepository;
import com.ironhack.MidtermProject.repository.users.RoleRepository;
import com.ironhack.MidtermProject.repository.users.ThirdPartyRepository;
import com.ironhack.MidtermProject.repository.users.UserRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class Data {

    final CheckingRepository checkingRepository;
    final CreditCardRepository creditCardRepository;
    final SavingsRepository savingsRepository;
    final StudentCheckingRepository studentCheckingRepository;
    final AccountRepository accountRepository;

    final AccountHolderRepository accountHolderRepository;
    final ThirdPartyRepository thirdPartyRepository;
    final AddressRepository addressRepository;
    final TransactionRepository transactionRepository;
    final UserRepository userRepository;
    final RoleRepository roleRepository;

    final TestsRepository testsRepository;

    public Data(CheckingRepository checkingRepository, CreditCardRepository creditCardRepository,
                SavingsRepository savingsRepository, StudentCheckingRepository studentCheckingRepository,
                AccountRepository accountRepository, AccountHolderRepository accountHolderRepository,
                AddressRepository addressRepository, TransactionRepository transactionRepository,
                ThirdPartyRepository thirdPartyRepository, UserRepository userRepository, RoleRepository roleRepository, TestsRepository testsRepository) {
        this.checkingRepository = checkingRepository;
        this.creditCardRepository = creditCardRepository;
        this.savingsRepository = savingsRepository;
        this.studentCheckingRepository = studentCheckingRepository;
        this.accountRepository = accountRepository;
        this.accountHolderRepository = accountHolderRepository;
        this.addressRepository = addressRepository;
        this.transactionRepository = transactionRepository;
        this.thirdPartyRepository = thirdPartyRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.testsRepository = testsRepository;
    }

    List<Checking> checkingList;
    List<CreditCard> creditCardList;
    List<Savings> savingsList;
    List<StudentChecking> studentCheckingList;

    List<AccountHolder> accountHolderList;
    List<ThirdParty> thirdPartyList;
    List<Address> addressList;
    List<Transaction> transactionList;
    List<User> userList;
    List<Role> roleList;

    public void populateRepos(){

        addressList = addressRepository.saveAll(List.of(
                new Address("Shire","Shire", "8", "12-345"),
                new Address("Gondor", "Mordor St", "45", "456-789"),
                new Address("Rohan", "Castle","12/100", "159-753")
        ));

        accountHolderList = accountHolderRepository.saveAll(List.of(
                new AccountHolder("Bilbo",LocalDate.of(1975,9,21), "bilbo@baggins.com",  addressList.get(0)),
                new AccountHolder("Frodo",LocalDate.of(2000,12,31), "frodo@baggins.com",  addressList.get(0)),
                new AccountHolder("Aragorn",LocalDate.of(1979,02,7), "aragorn@king.com",  addressList.get(1)),
                new AccountHolder("Eowina",LocalDate.of(1985,10,4), "eowina@rohan.com",  addressList.get(2))
        ));

        checkingList = checkingRepository.saveAll(List.of(
                new Checking(new Money(new BigDecimal(12000)), "Bilbo", "Frodo", "DEF", LocalDateTime.of(2021,9,14,0,0,0).toLocalDate(), accountHolderList.get(0)),
                new Checking(new Money(new BigDecimal(100)), "Aragorn", null, "EFG", LocalDateTime.of(2020,11,30,0,0,0).toLocalDate(), accountHolderList.get(2))
        ));

        studentCheckingList = studentCheckingRepository.saveAll(List.of(
                new StudentChecking(new Money(new BigDecimal(1100)), "Frodo", "Bilbo", "GHI", LocalDateTime.of(2021,4,4,0,0,0).toLocalDate(), accountHolderList.get(1))
        ));

        savingsList = savingsRepository.saveAll(List.of(
                new Savings(new Money(new BigDecimal(5000)), "Aragorn", null, "ABC", LocalDateTime.of(2020,2,16,0,0,0).toLocalDate(), accountHolderList.get(2), new BigDecimal("0"), new BigDecimal("0.")),
                new Savings(new Money(new BigDecimal(1000)), "Bilbo", null, "BCD",  LocalDateTime.of(2021,5,1,0,0,0).toLocalDate(), accountHolderList.get(0), new BigDecimal("110"), new BigDecimal("0.25"))
        ));

        creditCardList = creditCardRepository.saveAll(List.of(
                new CreditCard(new Money( new BigDecimal(1200)), "Eowina", null, "CDE", LocalDateTime.of(2021,8,14,0,0,0).toLocalDate(), accountHolderList.get(3), 100, new BigDecimal("0.2")),
                new CreditCard(new Money( new BigDecimal(500)), "Aragorn", null, "FGH", LocalDateTime.of(2020,3,19,0,0,0).toLocalDate(), accountHolderList.get(2), 100, new BigDecimal("0.2"))
        ));

        thirdPartyList = thirdPartyRepository.saveAll(List.of(
                new ThirdParty("Bank of Middle-earth","1XYZ"),
                new ThirdParty("Eye of Sauron Company", "7EYE")
        ));

        transactionList = transactionRepository.saveAll(List.of(
                new Transaction(4, 3, "3", "2", true, Operations.WITHDRAWAL, LocalDateTime.of(2021, 9, 18, 21,02,04), new BigDecimal("-417.15")),
                new Transaction(4, 3, "3", "2", true, Operations.DEPOSIT, LocalDateTime.of(2021, 9, 18, 21,02, 04), new BigDecimal("338.66")),
                new Transaction(4, 3, "1", "1", true, Operations.DEPOSIT, LocalDateTime.of(2021,9,18, 22,04,47), new BigDecimal("133.52")),
                new Transaction(3, 2, "1", "1", true, Operations.WITHDRAWAL, LocalDateTime.of(2021,9,19, 07,45,15), new BigDecimal("-560.05")),
                new Transaction(3, 4, "3", "2", true, Operations.WITHDRAWAL, LocalDateTime.of(2021,9,19, 07,56,07), new BigDecimal("-205.24")),
                new Transaction(3, 2, "4", "3", true, Operations.DEPOSIT, LocalDateTime.of(2021,9,19, 10,14,01), new BigDecimal("720.77")),
                new Transaction(1, 1, "1", "5", true, Operations.WITHDRAWAL, LocalDateTime.of(2021,9,19, 10,54,31), new BigDecimal("-125.8")),
                new Transaction(4, 3, "3", "4", true, Operations.WITHDRAWAL, LocalDateTime.of(2021,9,19, 14,54,10), new BigDecimal("-817.58")),
                new Transaction(4, 3, "2XYZ", null, false, Operations.WITHDRAWAL, LocalDateTime.of(2021,9,19, 15,29,44), new BigDecimal("-570.16")),
                new Transaction(1, 5, "3", "2", true, Operations.WITHDRAWAL, LocalDateTime.of(2021,9,19, 17,50,27), new BigDecimal("-789.11")),
                new Transaction(1, 1, "4", "3", true, Operations.DEPOSIT, LocalDateTime.of(2021,9,20, 05,06,47), new BigDecimal("711.52")),
                new Transaction(1, 5, "1", "1", true, Operations.WITHDRAWAL, LocalDateTime.of(2021,9,20, 7,38,58), new BigDecimal("-661.27")),
                new Transaction(3, 4, "1", "5", true, Operations.WITHDRAWAL, LocalDateTime.of(2021,9,20, 8,02,32), new BigDecimal("-157.2")),
                new Transaction(1, 1, "3", "4", true, Operations.WITHDRAWAL, LocalDateTime.of(2021,9,20, 11,27,32), new BigDecimal("-447.12")),
                new Transaction(4, 3, "1", "5", true, Operations.WITHDRAWAL, LocalDateTime.of(2021,9,20, 15,12,51), new BigDecimal("-16.58")),
                new Transaction(3, 2, "3", "4", true, Operations.WITHDRAWAL, LocalDateTime.of(2021,9,20, 18,00,42), new BigDecimal("-990.9")),
                new Transaction(4, 3, "2XYZ", null, false, Operations.WITHDRAWAL, LocalDateTime.of(2021,9,21, 9,38,10), new BigDecimal("-501.34")),
                new Transaction(1, 5, "4", "3", true, Operations.DEPOSIT, LocalDateTime.of(2021,9,21, 10,51,9), new BigDecimal("830.12")),
                new Transaction(3, 4, "4", "3", true, Operations.DEPOSIT, LocalDateTime.of(2021,9,21, 12,42,46), new BigDecimal("961.5")),
                new Transaction(3, 2, "1", "5", true, Operations.WITHDRAWAL, LocalDateTime.of(2021,9,21, 22,14,22), new BigDecimal("-903.36")),
                new Transaction(1, 1, "1XYZ", null, false, Operations.WITHDRAWAL, LocalDateTime.of(2021,9,22, 00,30,27), new BigDecimal("-980.49")),
                new Transaction(1, 5, "3", "2", true, Operations.WITHDRAWAL, LocalDateTime.of(2021,9,22, 04,52,22), new BigDecimal("-863.35")),
                new Transaction(3, 4, "1XYZ", null, false, Operations.WITHDRAWAL, LocalDateTime.of(2021,9,22, 05,12,37), new BigDecimal("-69.2")),
                new Transaction(1, 1, "4", "3", true, Operations.DEPOSIT, LocalDateTime.of(2021,9,22, 07,19,57), new BigDecimal("518.18")),
                new Transaction(4, 3, "3", "4", true, Operations.DEPOSIT, LocalDateTime.of(2021,9,22, 9,07,32), new BigDecimal("536.33")),
                new Transaction(3, 2, "4", "3", true, Operations.DEPOSIT, LocalDateTime.of(2021,9,22, 15,45,06), new BigDecimal("203.17")),
                new Transaction(3, 4, "1", "5", true, Operations.WITHDRAWAL, LocalDateTime.of(2021,9,23, 07,53,50), new BigDecimal("-877.77")),
                new Transaction(3, 2, "1", "5", true, Operations.WITHDRAWAL, LocalDateTime.of(2021,9,21, 22,14,22), new BigDecimal("-9.36")),
                new Transaction(3, 2, "1", "5", true, Operations.WITHDRAWAL, LocalDateTime.of(2021,9,21, 22,14,22), new BigDecimal("-3.36")),
                new Transaction(3, 2, "1", "5", true, Operations.WITHDRAWAL, LocalDateTime.of(2021,9,21, 22,14,22), new BigDecimal("-90.36")),
                new Transaction(1, 1, "4", "3", true, Operations.DEPOSIT, LocalDateTime.now().minusDays(1), new BigDecimal("1500.00"))
        ));

        userList = userRepository.saveAll(List.of(
                new User("account-holder", "$2a$10$MSzkrmfd5ZTipY0XkuCbAejBC9g74MAg2wrkeu8/m1wQGXDihaX3e"),
                new User("third-party", "$2a$15$NAYChDNT4kWKytFJLqNm0.vN9oNv0l9wa1acKQkDd7uSlgI6GpyIO"),
                new User("admin", "$2a$15$LNyWbikF3gn83ZnBBG1L8OxG9BXKe5smU2I5FF0quoJD9qtXIRBCm")
        ));

        roleList = roleRepository.saveAll(List.of(
                new Role("ACCOUNT-HOLDER", userList.get(0)),
                new Role("ADMIN", userList.get(2)),
                new Role("THIRD-PARTY", userList.get(1))
        ));

        System.out.println("DATA SUCCESSFULLY IMPLEMENTED");
    }

    public void cleanAllTables(){

        accountRepository.deleteAll();
        accountHolderRepository.deleteAll();
        addressRepository.deleteAll();
        thirdPartyRepository.deleteAll();
        transactionRepository.deleteAll();
        userRepository.deleteAll();
        roleRepository.deleteAll();

        testsRepository.alterTableAccount();
        testsRepository.alterTableAccountHolder();
        testsRepository.alterTableAddress();
        testsRepository.alterTableThirdParty();
        testsRepository.alterTableTransaction();
        testsRepository.alterTableUser();
        testsRepository.alterTableRole();
    }


}

