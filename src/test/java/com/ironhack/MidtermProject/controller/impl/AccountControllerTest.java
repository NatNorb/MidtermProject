package com.ironhack.MidtermProject.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.MidtermProject.Data;
import com.ironhack.MidtermProject.dao.additional.Money;
import com.ironhack.MidtermProject.dto.CheckingDTO;
import com.ironhack.MidtermProject.dto.SavingsDTO;
import com.ironhack.MidtermProject.enums.Status;
import com.ironhack.MidtermProject.repository.accounts.AccountRepository;
import com.ironhack.MidtermProject.repository.accounts.SavingsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;
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
class AccountControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private Data data;

    @Autowired
    private SavingsRepository savingsRepository;

    @Autowired
    private AccountRepository accountRepository;

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
    //    data.cleanAllTables();
    }

    @Test
    void search() throws Exception {
        MvcResult result = mockMvc.perform(get("/admin/account")).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("1"));
        assertTrue(result.getResponse().getContentAsString().contains("Eowina"));
        assertTrue(result.getResponse().getContentAsString().contains("40.00"));
    }

    @Test
    void transferMoney() throws Exception {
        SavingsDTO sDTO = new SavingsDTO();
        sDTO.setBalance(new Money(new BigDecimal("5100")));

        CheckingDTO chDTO = new CheckingDTO();
        chDTO.setBalance(new Money(new BigDecimal("11900")));

        String body = objectMapper.writeValueAsString(sDTO);
        mockMvc.perform(put("/account-holder/1/transfer/4/Bilbo/100").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent()).andReturn();

        assertEquals(new BigDecimal("5100.00"), accountRepository.findById(4l).get().getBalance().getAmount());
    }

    @Test
    void thirdPartyOperation() throws Exception{
        SavingsDTO sDTO = new SavingsDTO();
        sDTO.setBalance(new Money(new BigDecimal("5500")));

        String body = objectMapper.writeValueAsString(sDTO);
        mockMvc.perform(put("/third-party/7EYE/500/4/ABC/DEPOSIT").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent()).andReturn();

        assertEquals(new BigDecimal("5500.00"), accountRepository.findById(4l).get().getBalance().getAmount());
    }

    @Test
    void penaltyFee() throws Exception {
        CheckingDTO chDTO = new CheckingDTO();
        chDTO.setBalance(new Money(new BigDecimal("60")));

        String body = objectMapper.writeValueAsString(chDTO);
        mockMvc.perform(put("/admin/penaltyFee/2").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent()).andReturn();

        assertEquals(new BigDecimal("60.00"), accountRepository.findById(2l).get().getBalance().getAmount());
    }

    @Test
    void interestRate() throws Exception{
        SavingsDTO sDTO = new SavingsDTO();
        sDTO.setBalance(new Money(new BigDecimal("5012.5")));

        String body = objectMapper.writeValueAsString(sDTO);
        mockMvc.perform(put("/admin/interest/4").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent()).andReturn();

        assertEquals(new BigDecimal("5012.50"), accountRepository.findById(4l).get().getBalance().getAmount());
    }

    @Test
    void searchByName() throws Exception{
        MvcResult result = mockMvc.perform(get("/account-holder/Bilbo")).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("DEF"));
        assertTrue(result.getResponse().getContentAsString().contains("BCD"));
        assertFalse(result.getResponse().getContentAsString().contains("CDE"));
    }

    @Test
    void fraudDetectionV1() throws Exception {
        CheckingDTO chDTO = new CheckingDTO();
        chDTO.setStatus(Status.FROZEN);

        String body = objectMapper.writeValueAsString(chDTO);
        mockMvc.perform(put("/admin/fraud-detection-v1").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent()).andReturn();

        assertEquals(Status.FROZEN, accountRepository.findById(1l).get().getStatus());
    }

    @Test
    void fraudDetectionV2() throws Exception {
        CheckingDTO chDTO = new CheckingDTO();
        chDTO.setStatus(Status.FROZEN);

        String body = objectMapper.writeValueAsString(chDTO);
        mockMvc.perform(put("/admin/fraud-detection-v2").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent()).andReturn();

        assertEquals(Status.FROZEN, accountRepository.findById(2l).get().getStatus());
    }
}