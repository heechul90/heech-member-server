package com.heech.member.core.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heech.member.core.service.JoinService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(JoinController.class)
class JoinControllerTest {

    @Autowired private MockMvc mockMvc;

    @MockBean private JoinService joinService;

    @Autowired private ObjectMapper objectMapper;

    @Test
    void joinMember() {
    }
}