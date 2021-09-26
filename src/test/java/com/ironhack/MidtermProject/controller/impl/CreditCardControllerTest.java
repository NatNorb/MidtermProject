package com.ironhack.MidtermProject.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.MidtermProject.Data;
import com.ironhack.MidtermProject.dao.additional.Money;
import com.ironhack.MidtermProject.dto.CreditCardDTO;
import com.ironhack.MidtermProject.dto.SavingsDTO;
import com.ironhack.MidtermProject.dto.StudentCheckingDTO;
import com.ironhack.MidtermProject.repository.accounts.CreditCardRepository;
import com.ironhack.MidtermProject.repository.accounts.SavingsRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class CreditCardControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private Data data;

    @Autowired
    private CreditCardRepository creditCardRepository;

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
      //  data.cleanAllTables();
    }

    @Test
    void searchCreditCard() throws Exception {
        MvcResult result = mockMvc.perform(get("/admin/credit-card")).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Aragorn"));
        assertFalse(result.getResponse().getContentAsString().contains("BCD"));
        assertTrue(result.getResponse().getContentAsString().contains("Eowina"));
    }

    @Test
    void createCreditCard() throws Exception {
        CreditCardDTO ccDTO = new CreditCardDTO();
        Money m = new Money(BigDecimal.valueOf(1000));
        ccDTO.setId(101l);
        ccDTO.setBalance(m);
        ccDTO.setPrimaryOwner("Frodo");
        ccDTO.setSecretKey("JKL");
        ccDTO.setAccHolderId(2l);

        String body = objectMapper.writeValueAsString(ccDTO);
        MvcResult result = mockMvc.perform(post("/admin/credit-card").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Frodo"));
    }

    @Test
    void modifyCreditCardBalance() throws Exception {
        CreditCardDTO ccDTO = new CreditCardDTO();
        ccDTO.setBalance(new Money(BigDecimal.valueOf(1234567.89)));

        String body = objectMapper.writeValueAsString(ccDTO);
        mockMvc.perform(patch("/admin/credit-card/6").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent()).andReturn();
        assertEquals(creditCardRepository.findById(6l).get().getBalance().getAmount(), BigDecimal.valueOf(1234567.89));
    }
}