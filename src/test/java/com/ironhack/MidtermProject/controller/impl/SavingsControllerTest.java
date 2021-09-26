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
import com.ironhack.MidtermProject.dto.AccountHolderDTO;
import com.ironhack.MidtermProject.dto.SavingsDTO;
import com.ironhack.MidtermProject.dto.StudentCheckingDTO;
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
    private Data data;

    @Autowired
    private SavingsRepository savingsRepository;

    @Autowired
    private AccountHolderRepository accountHolderRepository;

    @Autowired
    private AddressRepository addressRepository;

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
  //      data.cleanAllTables();
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
        SavingsDTO sDTO = new SavingsDTO();
        Money m = new Money(BigDecimal.valueOf(1000));
        sDTO.setId(100l);
        sDTO.setBalance(m);
        sDTO.setPrimaryOwner("Frodo");
        sDTO.setSecretKey("IJK");
        sDTO.setAccHolderId(2l);
        sDTO.setMinimumBalance(BigDecimal.valueOf(999));

        String body = objectMapper.writeValueAsString(sDTO);
        MvcResult result = mockMvc.perform(post("/admin/savings").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Frodo"));
    }

    @Test
    void modifySavingsBalance() throws Exception {
        SavingsDTO sDTO = new SavingsDTO();
        sDTO.setBalance(new Money(new BigDecimal("1234567.89")));

        String body = objectMapper.writeValueAsString(sDTO);
        mockMvc.perform(patch("/admin/savings/4").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent()).andReturn();
        assertEquals(1234567.89, savingsRepository.findById(4l).get().getBalance().getAmount());
    }


}