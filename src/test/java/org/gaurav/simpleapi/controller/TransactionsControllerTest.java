package org.gaurav.simpleapi.controller;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc

class TransactionsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    void shouldReturnDefaultMessageForTransactionsByCustomerId() throws Exception {
        this.mockMvc.perform(get("/transactions/customers/10002")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"totalCost\":0}")));
    }

    @Test
    @Order(1)
    void shouldReturnDefaultMessageForTransactionsByProductId() throws Exception {
        this.mockMvc.perform(get("/transactions/products/PRODUCT_001")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"totalCost\":0}")));
    }

    @Test
    @Order(1)
    void shouldReturnDefaultMessageForTransactionsByLocation() throws Exception {
        this.mockMvc.perform(get("/transactions/locations/Australia")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"totalCost\":0}")));
    }

    //@Test
    @Order(2)
    @Sql({"/data_setup01.sql", "/tables_setup.sql"})
    void runSqls() {}


    @Test
    @Order(3)
    void shouldReturnDefaultMessageForTransactionsByLocation1() throws Exception {
        this.mockMvc.perform(get("/transactions/locations/Australia")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"totalCost\":0}")));
    }


}