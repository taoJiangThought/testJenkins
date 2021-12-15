package com.example.myapp.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.myapp.entity.TodoItem;
import org.junit.ClassRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;


import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ContextConfiguration(initializers = {TodoItemControllerIntegrationTest.Initializer.class})
@SpringBootTest
class TodoItemControllerIntegrationTest {


    public static void main(String[] args) {
        System.out.println((Object) null);
    }
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @ClassRule
    public static PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer("postgres:11.1")
            .withDatabaseName("root")
            .withUsername("test")
            .withPassword("123456");

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            postgresqlContainer.start();
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgresqlContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgresqlContainer.getUsername(),
                    "spring.datasource.password=" + postgresqlContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }


    @Test
    public void givenValidTodoItem_whenSaveTodoItem_thenSucceed()throws Exception{
        MvcResult mvcResult = this.mockMvc.perform(post("/todoItems").contentType(MediaType.APPLICATION_JSON_VALUE).content(
                "{\n" +
                        "  \"eventName\":\"test7\",\n" +
                        "  \"status\":0\n" +
                        "}")).andExpect(status().isOk()).andExpect(jsonPath("$.id").isNumber()).andReturn();
    }

    @Test
    public void whenGetAllTodoItems_thenSucceed()throws Exception{
        MvcResult mvcResult = this.mockMvc.perform(get("/todoItems"))
                .andExpect(status().isOk()).andReturn();
    }


    @Test
    public  void givenValidTodoItem_whenUpdateTodoItem_thenSucceed() throws Exception{
        //create todoItem
        MvcResult addMvcResult = this.mockMvc.perform(post("/todoItems").contentType(MediaType.APPLICATION_JSON_VALUE).content(
                        "{\n" +
                                "  \"eventName\":\"test_update\",\n" +
                                "  \"status\":0\n" +
                                "}"))
                .andExpect(status().isOk()).andReturn();
        String contentAsString = addMvcResult.getResponse().getContentAsString();
        TodoItem todoItem = JSONObject.parseObject(contentAsString, TodoItem.class);
        //update
        MvcResult mvcResult = this.mockMvc.perform(put("/todoItems?id={id}&status={status}",todoItem.getId(),1))
               .andExpect(status().isOk()).andReturn();
    }

    @Test
    public  void givenValidTodoItemId_whenDeleteTodoItem_thenSucceed() throws Exception{
        //create todoItem
        MvcResult addMvcResult = this.mockMvc.perform(post("/todoItems").contentType(MediaType.APPLICATION_JSON_VALUE).content(
                        "{\n" +
                                "  \"eventName\":\"test_update\",\n" +
                                "  \"status\":0\n" +
                                "}"))
                .andExpect(status().isOk()).andReturn();
        String contentAsString = addMvcResult.getResponse().getContentAsString();
        TodoItem todoItem = JSONObject.parseObject(contentAsString, TodoItem.class);

        //delete
        MvcResult mvcResult = this.mockMvc.perform(delete("/todoItems/{id}", todoItem.getId()))
                .andExpect(status().isOk()).andReturn();
    }

}