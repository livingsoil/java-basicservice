package com.example.basicservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class AdventurerControllerTests {

    @Autowired // injects AdventurerController
    private AdventurerController controller;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    String[] expectedResponses = {"[{\"name\":\"Trog\",\"adventurerClass\":\"fighter\",\"health\":20,\"mana\":20},{\"name\":\"Skiz\",\"adventurerClass\":\"mage\",\"health\":10,\"mana\":50}]", "{\"name\":\"Trog\",\"adventurerClass\":\"fighter\",\"health\":20,\"mana\":20}"};

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnAllAdventurers() throws Exception {
        this.mockMvc.perform(get("/adventurer")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(expectedResponses[0])));
    }

    @Test
    public void shouldReturnAdventurerByIndex() throws Exception {

        this.mockMvc.perform(get("/adventurer/index/0")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(expectedResponses[1])));
    }
}