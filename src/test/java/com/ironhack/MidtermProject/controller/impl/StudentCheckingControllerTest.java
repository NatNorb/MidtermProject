package com.ironhack.MidtermProject.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.MidtermProject.Data;
import com.ironhack.MidtermProject.dao.accounts.StudentChecking;
import com.ironhack.MidtermProject.dao.additional.Money;
import com.ironhack.MidtermProject.dto.StudentCheckingDTO;
import com.ironhack.MidtermProject.repository.accounts.StudentCheckingRepository;
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

import javax.management.Query;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class StudentCheckingControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private Data data;

    @Autowired
    private StudentCheckingRepository studentCheckingRepository;

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
      // data.cleanAllTables();
       
    }

    @Test
    void searchStudentChecking() throws Exception {
        MvcResult result = mockMvc.perform(get("/admin/student")).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Frodo"));
        assertFalse(result.getResponse().getContentAsString().contains("Aragorn"));
        assertTrue(result.getResponse().getContentAsString().contains("Bilbo"));
    }

    @Test
    void modifyCheckingBalance() throws Exception {
        StudentCheckingDTO schDTO = new StudentCheckingDTO();
        schDTO.setBalance(new Money(new BigDecimal("1234567.89")));

        String body = objectMapper.writeValueAsString(schDTO);
        mockMvc.perform(patch("/admin/student/3").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent()).andReturn();
        assertEquals(studentCheckingRepository.findById(3l).get().getBalance().getAmount(), BigDecimal.valueOf(1234567.89));

    }
}