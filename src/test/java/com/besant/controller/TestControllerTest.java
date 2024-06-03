package com.besant.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
@WebMvcTest(TestController.class)
public class TestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testTypicalName() throws Exception {
        mockMvc.perform(get("/api/user")
                .param("userName", "John"))
                .andExpect(status().isOk())
                .andExpect(content().string("Welcome Mr. John"));
    }

    @Test
    public void testEmptyString() throws Exception {
        mockMvc.perform(get("/api/user")
                .param("userName", ""))
                .andExpect(status().isOk())
                .andExpect(content().string("Welcome Mr. "));
    }

    @Test
    public void testNameWithSpaces() throws Exception {
        mockMvc.perform(get("/api/user")
                .param("userName", "John Doe"))
                .andExpect(status().isOk())
                .andExpect(content().string("Welcome Mr. John Doe"));
    }

    @Test
    public void testNameWithSpecialCharacters() throws Exception {
        mockMvc.perform(get("/api/user")
                .param("userName", "@John#"))
                .andExpect(status().isOk())
                .andExpect(content().string("Welcome Mr. @John#"));
    }

    @Test
    public void testNullName() throws Exception {
        mockMvc.perform(get("/api/user"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testNameWithNumbers() throws Exception {
        mockMvc.perform(get("/api/user")
                .param("userName", "John123"))
                .andExpect(status().isOk())
                .andExpect(content().string("Welcome Mr. John123"));
    }

    @Test
    public void testVeryLongName() throws Exception {
        String longName = "John".repeat(1000);
        mockMvc.perform(get("/api/user")
                .param("userName", longName))
                .andExpect(status().isOk())
                .andExpect(content().string("Welcome Mr. " + longName));
    }
}
