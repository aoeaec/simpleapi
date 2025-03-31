package org.gaurav.simpleapi.controller;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
@AutoConfigureMockMvc(addFilters = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TransactionsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"/delete_transactions_table.sql"})
    void shouldReturnDefaultMessageForTransactionsByCustomerId() throws Exception {
        this.mockMvc.perform(get("/transactions/customers/10002")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"totalCost\":0}")));
    }

    @Test
    @Order(1)
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"/delete_transactions_table.sql"})
    void shouldReturnDefaultMessageForTransactionsByProductId() throws Exception {
        this.mockMvc.perform(get("/transactions/products/PRODUCT_001")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"totalCost\":0}")));
    }

    @Test
    @Order(1)
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"/delete_transactions_table.sql"})
    void shouldReturnDefaultMessageForTransactionsByLocation() throws Exception {
        this.mockMvc.perform(get("/transactions/locations/Australia")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"totalCost\":0}")));
    }


    @Test
    @Order(3)
    @Sql({"/data_setup01.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = {"/delete_transactions_table.sql"})
    void shouldReturnDefaultMessageForTransactionsByLocation1() throws Exception {
        this.mockMvc.perform(get("/transactions/locations/Australia")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("{\"totalCost\":2000,\"transactionsList\":[{\"transactionTime\":\"2018-01-01T14:56:00.000+00:00\",\"customerId\":10002,\"quantity\":5,\"productCode\":\"PRODUCT_004\",\"cost\":250,\"location\":\"Australia\"},{\"transactionTime\":\"2018-01-01T14:56:00.000+00:00\",\"customerId\":10002,\"quantity\":5,\"productCode\":\"PRODUCT_001\",\"cost\":250,\"location\":\"Australia\"},{\"transactionTime\":\"2018-01-01T14:56:00.000+00:00\",\"customerId\":10002,\"quantity\":5,\"productCode\":\"PRODUCT_004\",\"cost\":250,\"location\":\"Australia\"},{\"transactionTime\":\"2018-01-01T14:56:00.000+00:00\",\"customerId\":10001,\"quantity\":5,\"productCode\":\"PRODUCT_001\",\"cost\":250,\"location\":\"Australia\"},{\"transactionTime\":\"2018-01-01T14:56:00.000+00:00\",\"customerId\":10003,\"quantity\":5,\"productCode\":\"PRODUCT_004\",\"cost\":250,\"location\":\"Australia\"},{\"transactionTime\":\"2018-01-01T14:56:00.000+00:00\",\"customerId\":10005,\"quantity\":5,\"productCode\":\"PRODUCT_002\",\"cost\":250,\"location\":\"Australia\"},{\"transactionTime\":\"2018-01-01T14:56:00.000+00:00\",\"customerId\":10004,\"quantity\":5,\"productCode\":\"PRODUCT_005\",\"cost\":250,\"location\":\"Australia\"},{\"transactionTime\":\"2018-01-01T14:56:00.000+00:00\",\"customerId\":10004,\"quantity\":5,\"productCode\":\"PRODUCT_003\",\"cost\":250,\"location\":\"Australia\"}]}"));
    }

}