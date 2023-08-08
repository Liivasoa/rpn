package com.katas.rpn;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
	@DisplayName("Will return the stack with stack_id = 1 and stack_value should be [5]")
    public void testGetStackByIdOk() throws Exception {
        mockMvc.perform(get("/rpn/stack/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.stackValue", is(equalTo(Lists.newArrayList(5)))));
        
        mockMvc.perform(get("/rpn/stack/26"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").doesNotExist());
    }
	
	@Test
	@DisplayName("Will return null because the stack is not found")
    public void testGetStackByIdNotFound() throws Exception {        
        mockMvc.perform(get("/rpn/stack/26"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").doesNotExist());
    }

}
