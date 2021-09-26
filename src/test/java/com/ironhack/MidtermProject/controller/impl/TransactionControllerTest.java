package com.ironhack.MidtermProject.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.MidtermProject.Data;
import com.ironhack.MidtermProject.dao.additional.Transaction;
import com.ironhack.MidtermProject.enums.Operations;
import com.ironhack.MidtermProject.repository.additional.TransactionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class TransactionControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private TransactionRepository transactionRepository;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        List<Transaction> transactionList = transactionRepository.saveAll(
                List.of(
                    new Transaction(4, 3, "3", "2", true, Operations.WITHDRAWAL, LocalDateTime.of(2021, 9, 18, 21,02,04), new BigDecimal("-417.15")),
                    new Transaction(4, 3, "3", "2", true, Operations.DEPOSIT, LocalDateTime.of(2021, 9, 18, 21,02, 04), new BigDecimal("338.66")),
                    new Transaction(4, 3, "1", "1", true, Operations.DEPOSIT, LocalDateTime.of(2021,9,19, 22,04,47), new BigDecimal("133.52"))
                )
        );
    }

    @AfterEach
    void tearDown() {
        transactionRepository.deleteAll();
    }

    @Test
    void search() throws Exception {
        MvcResult result = mockMvc.perform(get("/admin/transaction")).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("WITHDRAWAL"));
        assertTrue(result.getResponse().getContentAsString().contains("338.66"));
        assertTrue(result.getResponse().getContentAsString().contains("2021-09-19"));
        assertFalse(result.getResponse().getContentAsString().contains("Bilbo"));
    }
}