package com.oocl.web.sampleWebApp;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.HashMap;
import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello World")));
    }
    
    @Test
    public void shouldReturnBadRequestMessage() throws Exception {
        this.mockMvc.perform(get("/hello")).andDo(print()).andExpect(status().isBadRequest());
    }
    
    @Test
    public void shouldReturnMapMessage() throws Exception {
        this.mockMvc.perform(get("/map"))
         .andDo(print())
         .andExpect(status().isOk())
         .andExpect(MockMvcResultMatchers.jsonPath("$.user", CoreMatchers.is("name")));
    }
    
    @Test
    public void shouldReturnMapMessageWhenCallPostMethod() throws Exception {
     Map<String, String> map = new HashMap<String, String>();
     map.put("id", "ID0001");
     String postString = objectMapper.writeValueAsString(map);
        this.mockMvc.perform(MockMvcRequestBuilders
          .post("/post")
          .contentType(MediaType.APPLICATION_JSON)
          .content(postString))
         .andDo(print())
         .andExpect(status().is(201))
         .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is("ID0001")));
    }
}


