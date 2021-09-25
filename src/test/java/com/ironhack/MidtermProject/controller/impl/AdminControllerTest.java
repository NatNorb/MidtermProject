package com.ironhack.MidtermProject.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.MidtermProject.Data;
import com.ironhack.MidtermProject.dao.additional.Address;
import com.ironhack.MidtermProject.dao.additional.Transaction;
import com.ironhack.MidtermProject.dao.users.AccountHolder;
import com.ironhack.MidtermProject.dao.users.ThirdParty;
import com.ironhack.MidtermProject.enums.Operations;
import com.ironhack.MidtermProject.repository.additional.AddressRepository;
import com.ironhack.MidtermProject.repository.users.AccountHolderRepository;
import com.ironhack.MidtermProject.repository.users.ThirdPartyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AdminControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    Data data;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        data.populateRepos();
//        List<ThirdParty> thirdPartyList = thirdPartyRepository.saveAll(
//                List.of(
//                        new ThirdParty("Bank of Middle-earth","1XYZ"),
//                        new ThirdParty("Eye of Sauron Company", "7EYE")
//                )
//        );
//        List<Address> addressList = addressRepository.saveAll(
//                List.of(
//                        new Address("Shire","Shire", "8", "12-345"),
//                        new Address("Gondor", "Mordor St", "45", "456-789"),
//                        new Address("Rohan", "Castle","12/100", "159-753")
//                )
//        );
//        List<AccountHolder> accountHolderList = accountHolderRepository.saveAll(
//                List.of(
//                        new AccountHolder("Bilbo", java.time.LocalDate.of(1975,9,21), "bilbo@baggins.com",  addressList.get(0)),
//                        new AccountHolder("Frodo", java.time.LocalDate.of(2000,12,31), "frodo@baggins.com",  addressList.get(0)),
//                        new AccountHolder("Aragorn", java.time.LocalDate.of(1979,02,7), "aragorn@king.com",  addressList.get(1)),
//                        new AccountHolder("Eowina", java.time.LocalDate.of(1985,10,4), "eowina@rohan.com",  addressList.get(2))
//                )
//        );


    }

    @AfterEach
    void tearDown() {
        data.cleanAllTables();
    }

    @Test
    void searchAccountHolders() throws Exception {
        MvcResult result = mockMvc.perform(get("/admin/account-holder")).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Eowina"));
        assertTrue(result.getResponse().getContentAsString().contains("1979"));
        assertTrue(result.getResponse().getContentAsString().contains("bilbo@baggins.com"));

    }

    @Test
    void searchThirdParty() throws Exception {
        MvcResult result = mockMvc.perform(get("/admin/third-party")).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Bank of Middle-earth"));
        assertTrue(result.getResponse().getContentAsString().contains("7EYE"));
    }

    @Test
    void searchAddress() throws Exception {
        MvcResult result = mockMvc.perform(get("/admin/address")).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Shire"));
        assertTrue(result.getResponse().getContentAsString().contains("Mordor St"));
        assertTrue(result.getResponse().getContentAsString().contains("159-753"));
    }

    @Test
    void createAccountHolder() throws Exception {
        AccountHolder newAccHolder = new AccountHolder("Arwen",
                LocalDate.of(1986,11,2), "arwen@elf.com",
                new Address("Gondor", "Mordor St", "45", "456-789"));
        String body = objectMapper.writeValueAsString(newAccHolder);
        MvcResult result = mockMvc.perform(post("/admin/add/account-holder").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Arwen"));


    }

    @Test
    void updateAccountHolder() {
    }

    @Test
    void createAdmin() {
    }

    @Test
    void createThirdParty() throws Exception {
        ThirdParty newThirdParty = new ThirdParty("Gandalf & Friends", "8GAF");
        String body = objectMapper.writeValueAsString(newThirdParty);
        MvcResult result = mockMvc.perform(post("/admin/add/third-party").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Gandalf"));
    }

    @Test
    void createAddress() throws Exception {
        Address newAddress = new Address("Rivendell","Elves str", "1", "13545");
        String body = objectMapper.writeValueAsString(newAddress);
        MvcResult result = mockMvc.perform(post("/admin/add/address").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Rivendell"));
    }

    @Test
    void unfreeze() {

    }
}