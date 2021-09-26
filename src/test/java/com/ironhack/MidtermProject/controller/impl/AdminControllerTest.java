package com.ironhack.MidtermProject.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.MidtermProject.Data;
import com.ironhack.MidtermProject.dao.accounts.Account;
import com.ironhack.MidtermProject.dao.accounts.Checking;
import com.ironhack.MidtermProject.dao.additional.Address;
import com.ironhack.MidtermProject.dao.additional.Money;
import com.ironhack.MidtermProject.dao.additional.Transaction;
import com.ironhack.MidtermProject.dao.users.AccountHolder;
import com.ironhack.MidtermProject.dao.users.ThirdParty;
import com.ironhack.MidtermProject.dto.CheckingDTO;
import com.ironhack.MidtermProject.dto.SavingsDTO;
import com.ironhack.MidtermProject.enums.Operations;
import com.ironhack.MidtermProject.enums.Status;
import com.ironhack.MidtermProject.repository.accounts.AccountRepository;
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
import java.util.Date;
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

    @Autowired
    AccountRepository accountRepository;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        data.cleanAllTables();
        data.populateRepos();
    }

    @AfterEach
    void tearDown() {
        //data.cleanAllTables();
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
        AccountHolder newAccHolder = new AccountHolder();
        newAccHolder.setName("Arwen");
        newAccHolder.setMailingAddress("arwen@elf.com");

        String body = objectMapper.writeValueAsString(newAccHolder);
        MvcResult result = mockMvc.perform(post("/admin/add/account-holder").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Arwen"));
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
    void createAddress() throws Exception{
        Address address = new Address();
        address.setAddressId(10l);
        address.setCity("Paris");
        address.setPostCode("12345");
        address.setStreet("BackerStreet");

        String body = objectMapper.writeValueAsString(address);
        MvcResult result = mockMvc.perform(post("/admin/address").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Paris"));
    }

    @Test
    void unfreeze()  throws Exception{
        Checking checking = new Checking();
        checking.setStatus(Status.ACTIVE);
        String body = objectMapper.writeValueAsString(checking);
        mockMvc.perform(put("/unfreeze/1").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent()).andReturn();
        assertEquals(Status.ACTIVE, accountRepository.findById(1l).get().getStatus());

    }

}