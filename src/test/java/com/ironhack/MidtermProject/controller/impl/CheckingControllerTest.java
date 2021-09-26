package com.ironhack.MidtermProject.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.MidtermProject.Data;
import com.ironhack.MidtermProject.dao.accounts.Checking;
import com.ironhack.MidtermProject.dao.additional.Money;
import com.ironhack.MidtermProject.dao.users.AccountHolder;
import com.ironhack.MidtermProject.dto.CheckingDTO;
import com.ironhack.MidtermProject.dto.SavingsDTO;
import com.ironhack.MidtermProject.dto.StudentCheckingDTO;
import com.ironhack.MidtermProject.repository.accounts.CheckingRepository;
import com.ironhack.MidtermProject.repository.accounts.CreditCardRepository;
import com.ironhack.MidtermProject.repository.users.AccountHolderRepository;
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
class CheckingControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private Data data;

    @Autowired
    private CheckingRepository checkingRepository;

    @Autowired
    private AccountHolderRepository accountHolderRepository;

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
    void searchChecking() throws Exception {
        MvcResult result = mockMvc.perform(get("/admin/checking")).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Aragorn"));
        assertFalse(result.getResponse().getContentAsString().contains("Eowina"));
        assertTrue(result.getResponse().getContentAsString().contains("DEF"));
    }

    @Test
    void createChecking() throws Exception{
            CheckingDTO chDTO = new CheckingDTO();
            Money m = new Money(new BigDecimal ("1000"));
            chDTO.setId(111l);
            chDTO.setBalance(m);
            chDTO.setPrimaryOwner("Bilbo");
            chDTO.setSecretKey("KLM");
            chDTO.setAccHolderId(1l);

            String body = objectMapper.writeValueAsString(chDTO);
            MvcResult result = mockMvc.perform(post("/admin/checking").content(body)
                    .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
            assertTrue(result.getResponse().getContentAsString().contains("Bilbo"));
    }

    @Test
    void modifyCheckingBalance() throws Exception{
        CheckingDTO chDTO = new CheckingDTO();
        chDTO.setBalance(new Money(new BigDecimal("1234567.89")));

        String body = objectMapper.writeValueAsString(chDTO);
        mockMvc.perform(patch("/admin/checking/1").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent()).andReturn();
        assertEquals(checkingRepository.findById(1l).get().getBalance().getAmount(), BigDecimal.valueOf(1234567.89));
    }
}