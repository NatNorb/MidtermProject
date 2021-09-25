package com.ironhack.MidtermProject.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.MidtermProject.Data;
import com.ironhack.MidtermProject.repository.accounts.AccountRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AccountControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private Data data;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        data.populateRepos();
    }

    @AfterEach
    void tearDown() {
        data.cleanAllTables();
    }

    @Test
    void search() throws Exception {
        MvcResult result = mockMvc.perform(get("/admin/account")).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("1"));
        assertTrue(result.getResponse().getContentAsString().contains("Eowina"));
        assertTrue(result.getResponse().getContentAsString().contains("40.00"));
    }

    @Test
    void transferMoney() {
    }

    @Test
    void thirdPartyOperation() {
    }

    @Test
    void penaltyFee() {
    }

    @Test
    void interestRate() {
    }

    @Test
    void searchByName() {
    }

    @Test
    void fraudDetectionV1() {
    }

    @Test
    void fraudDetectionV2() {
    }
}