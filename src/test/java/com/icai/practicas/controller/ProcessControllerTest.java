package com.icai.practicas.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
public class ProcessControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProcessController processController;

    @Test
    public void legacy_endpoint_performs_correctly() {
        
    }

    @Test
    public void process_step1_endpoint_performs_correctly() {
        
    }
}
