package com.katas.rpn;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class StackControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@Test
	@DisplayName("Test getStackById() with correct stack id")
    public void testGetStackByIdOk() throws Exception {
        mockMvc.perform(get("/rpn/stack/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.stackValue", is(equalTo(Lists.newArrayList(5)))));
    }
	
	@Test
	@DisplayName("Test getStackById() with wrong stack id")
    public void testGetStackByIdNotFound() throws Exception {        
        mockMvc.perform(get("/rpn/stack/26"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").doesNotExist());
    }
	
	@Test
	@DisplayName("Test resetStack() with correct id")
    public void testResetStackOk() throws Exception {
        mockMvc.perform(put("/rpn/stack/2"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.stackValue", is(equalTo(Lists.newArrayList()))));
    }
	
	@Test
	@DisplayName("Test resetStack() with wrong id")
    public void testResetStackIdNotFound() throws Exception {        
        mockMvc.perform(put("/rpn/stack/21"))
        .andExpect(status().isBadRequest())
        .andExpect(content().string("No stack found, please verify your stackId"));
    }
	
	@Test
	@DisplayName("Test pushValue() with correct id and correct value")
    public void testPushValueOk() throws Exception {
        mockMvc.perform(patch("/rpn/stack/2").param("value", "10"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.stackValue", is(equalTo(Lists.newArrayList(10)))));
        
        mockMvc.perform(patch("/rpn/stack/2").param("value", "5"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.stackValue", is(equalTo(Lists.newArrayList(10, 5)))));
        
        mockMvc.perform(patch("/rpn/stack/2").param("value", "3"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.stackValue", is(equalTo(Lists.newArrayList(10, 5, 3)))));
    }
	
	@Test
	@DisplayName("Test pushValue() with wrong id")
    public void testPushValueIdNotFound() throws Exception {        
		mockMvc.perform(patch("/rpn/stack/21").param("value", "10"))
        .andExpect(status().isBadRequest())
        .andExpect(content().string("No stack found, please verify your stackId"));
    }

}
