package com.main.practica5.controller;

import com.main.controller.AppController;
import com.main.services.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.http.MediaType; 

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;

import java.util.Iterator;
import java.util.List;

@WebMvcTest({AppController.class})
public class AppControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Apod apod;

    @MockBean
    private Neo neo;
    
    @Test
    public void testApodReturnsCorrectInformationAndFormat() throws Exception {
        List<String> expected = List.of("date","explanation","hdurl","media_type","service_version","title","url");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/test/java/com/main/practica5/controller/apod.txt")))) {
            StringBuffer sb = new StringBuffer();
            String str;

            while((str = reader.readLine())!= null){
                sb.append(str);
            }

            when(apod.getApodInfo()).thenReturn(sb.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

        ResultActions response = this.mockMvc.perform(get("/apod").contentType(MediaType.APPLICATION_JSON_VALUE));
        Iterator<String> e = expected.iterator();

        while(e.hasNext()) {
            response.andExpect(content().string(containsString(asJsonString(e.next()))));
        }
                
    }

    @Test
    public void testNeoReturnsCorrectInformationAndFormat() throws Exception {
        List<String> expected = List.of("links","element_count","near_earth_objects");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/test/java/com/main/practica5/controller/neo.txt")))) {
            StringBuffer sb = new StringBuffer();
            String str;

            while((str = reader.readLine())!= null){
                sb.append(str);
            }

            when(neo.getNeoInfo()).thenReturn(sb.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

        ResultActions response = this.mockMvc.perform(get("/neo").contentType(MediaType.APPLICATION_JSON_VALUE));
        Iterator<String> e = expected.iterator();

        while(e.hasNext()) {
            response.andExpect(content().string(containsString(asJsonString(e.next()))));
        }
    }

    public static String asJsonString(Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
