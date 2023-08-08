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
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StackControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@Test
	@Order(1)
	@DisplayName("Test getStackById() with correct stack id")
    public void testGetStackByIdOk() throws Exception {
        mockMvc.perform(get("/rpn/stack/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.stackValue", is(equalTo(Lists.newArrayList(5)))));
    }
	
	@Test
	@Order(2)
	@DisplayName("Test getStackById() with wrong stack id")
    public void testGetStackByIdNotFound() throws Exception {        
        mockMvc.perform(get("/rpn/stack/26"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").doesNotExist());
    }
	
	@Test
	@Order(3)
	@DisplayName("Test resetStack() with correct id")
    public void testResetStackOk() throws Exception {
        mockMvc.perform(put("/rpn/stack/2"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.stackValue", is(equalTo(Lists.newArrayList()))));
    }
	
	@Test
	@Order(4)
	@DisplayName("Test resetStack() with wrong id")
    public void testResetStackIdNotFound() throws Exception {        
        mockMvc.perform(put("/rpn/stack/21"))
        .andExpect(status().isBadRequest())
        .andExpect(content().string("No stack found, please verify your stackId"));
    }
	
	@Test
	@Order(5)
	@DisplayName("Test pushValue() with correct id and correct value")
    public void testPushValueOk() throws Exception {
        mockMvc.perform(patch("/rpn/stack/2").param("value", "10"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.stackValue", is(equalTo(Lists.newArrayList(10)))));
        
        mockMvc.perform(patch("/rpn/stack/2").param("value", "5"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.stackValue", is(equalTo(Lists.newArrayList(10, 5)))));
        
        mockMvc.perform(patch("/rpn/stack/2").param("value", "6"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.stackValue", is(equalTo(Lists.newArrayList(10, 5, 6)))));
    }
	
	@Test
	@Order(6)
	@DisplayName("Test pushValue() with wrong id")
    public void testPushValueIdNotFound() throws Exception {        
		mockMvc.perform(patch("/rpn/stack/21").param("value", "10"))
        .andExpect(status().isBadRequest())
        .andExpect(content().string("No stack found, please verify your stackId"));
    }
	
	@Test
	@Order(7)
	@DisplayName("Test operation() with correct operator and stack")
    public void testOperationOk() throws Exception {
        mockMvc.perform(patch("/rpn/op/add/stack/2"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.stackValue", is(equalTo(Lists.newArrayList(10, 11)))));
    }
	
	@Test
	@Order(8)
	@DisplayName("Test operation() with correct operator and stack")
    public void testOperationOk2() throws Exception {
        mockMvc.perform(patch("/rpn/op/add/stack/2"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.stackValue", is(equalTo(Lists.newArrayList(21)))));
    }
	
	@Test
	@Order(9)
	@DisplayName("Test operation() with invalid stack")
    public void testOperationInvalidStack() throws Exception {
        mockMvc.perform(patch("/rpn/op/add/stack/2"))
        .andExpect(status().isBadRequest())
        .andExpect(content().string("Invalid stack"));
    }
	
	@Test
	@Order(10)
	@DisplayName("Test operation() with wrong operator")
    public void testOperationOperatorNotFound() throws Exception {
        mockMvc.perform(patch("/rpn/op/ajout/stack/2"))
        .andExpect(status().isBadRequest());
    }
	
	@Test
	@Order(11)
	@DisplayName("Test operation() with wrong inexisted stack id")
    public void testOperationIdNotFound() throws Exception {
        mockMvc.perform(patch("/rpn/op/add/stack/21"))
        .andExpect(status().isBadRequest())
        .andExpect(content().string("No stack found, please verify your stackId"));
    }

}
