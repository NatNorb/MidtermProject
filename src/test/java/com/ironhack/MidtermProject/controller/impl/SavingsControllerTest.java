package com.ironhack.MidtermProject.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.MidtermProject.Data;
import com.ironhack.MidtermProject.dao.accounts.Account;
import com.ironhack.MidtermProject.dao.accounts.Savings;
import com.ironhack.MidtermProject.dao.additional.Address;
import com.ironhack.MidtermProject.dao.additional.Money;
import com.ironhack.MidtermProject.dao.additional.Transaction;
import com.ironhack.MidtermProject.dao.users.AccountHolder;
import com.ironhack.MidtermProject.repository.accounts.AccountRepository;
import com.ironhack.MidtermProject.repository.accounts.SavingsRepository;
import com.ironhack.MidtermProject.repository.additional.AddressRepository;
import com.ironhack.MidtermProject.repository.additional.TransactionRepository;
import com.ironhack.MidtermProject.repository.users.AccountHolderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.scheduling.SchedulingAwareRunnable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class SavingsControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private SavingsRepository savingsRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AccountHolderRepository accountHolderRepository;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        List<Address> addressList = addressRepository.saveAll(List.of(
                new Address("Shire","Shire", "8", "12-345"),
                new Address("Gondor", "Mordor St", "45", "456-789"),
                new Address("Rohan", "Castle","12/100", "159-753")
        ));

        List<AccountHolder> accountHolderList = accountHolderRepository.saveAll(List.of(
                new AccountHolder("Bilbo", LocalDate.of(1975,9,21), "bilbo@baggins.com",  addressList.get(0)),
                new AccountHolder("Frodo",LocalDate.of(2000,12,31), "frodo@baggins.com",  addressList.get(0)),
                new AccountHolder("Aragorn",LocalDate.of(1979,02,7), "aragorn@king.com",  addressList.get(1)),
                new AccountHolder("Eowina",LocalDate.of(1985,10,4), "eowina@rohan.com",  addressList.get(2))
        ));

        List<Savings> transactionList = savingsRepository.saveAll(
                List.of(
                        new Savings(new Money(new BigDecimal(5000)), "Aragorn", null, "ABC", accountHolderList.get(2), new BigDecimal("0"), new BigDecimal("0")),
                        new Savings(new Money(new BigDecimal(1000)), "Bilbo", null, "BCD",  accountHolderList.get(0), new BigDecimal("110"), new BigDecimal("0.25"))
        ));
    }

    @AfterEach
    void tearDown() {
        savingsRepository.deleteAll();
        accountHolderRepository.deleteAll();
        addressRepository.deleteAll();
    }

    @Test
    void searchSaving() throws Exception {
        MvcResult result = mockMvc.perform(get("/admin/savings")).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Aragorn"));
        assertTrue(result.getResponse().getContentAsString().contains("BCD"));
        assertFalse(result.getResponse().getContentAsString().contains("Eowina"));


    }

    @Test
    void createSaving() throws Exception {
        Savings newSaving = new Savings(
                new Money(new BigDecimal(1000)), "Frodo", null, "IJK",  accountHolderRepository.getById(2l), new BigDecimal("5010"), new BigDecimal("0.25"));
        String body = objectMapper.writeValueAsString(newSaving);
        MvcResult result = mockMvc.perform(post("/admin/savings").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Frodo"));
    }

    @Test
    void modifySavingsBalance() throws Exception {
        Savings updateBalance = new Savings();
        updateBalance.setBalance(new Money(new BigDecimal("10")));

        String body = objectMapper.writeValueAsString(updateBalance);
        mockMvc.perform(patch("/savings/1").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent()).andReturn();
        assertEquals(accountRepository.findById(1l).get().getBalance(), 10);

    }
}