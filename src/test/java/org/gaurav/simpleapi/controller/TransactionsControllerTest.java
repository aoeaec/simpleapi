package org.gaurav.simpleapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.gaurav.simpleapi.model.dto.RequestDto;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @Order(1)
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"/delete_transactions_table.sql"})
    void shouldReturnDefaultMessageForTransactionsByCustomerId() throws Exception {
        this.mockMvc.perform(get("/transactions/customers/10002")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"message\":\"No records found\"}")));
    }

    @Test
    @Order(1)
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"/delete_transactions_table.sql"})
    void shouldReturnDefaultMessageForTransactionsByProductId() throws Exception {
        this.mockMvc.perform(get("/transactions/products/PRODUCT_001")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"message\":\"No records found\"}")));
    }

    @Test
    @Order(1)
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"/delete_transactions_table.sql"})
    void shouldReturnDefaultMessageForTransactionsByLocation() throws Exception {
        this.mockMvc.perform(get("/transactions/locations/Australia")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"message\":\"No records found\"}")));
    }


    @Test
    @Order(3)
    @Sql({"/data_setup01.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = {"/delete_transactions_table.sql"})
    void shouldReturnDefaultMessageForTransactionsByLocation1() throws Exception {
        this.mockMvc.perform(get("/transactions/locations/Australia")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("{\"totalCount\":8,\"transactionsList\":[{\"transactionTime\":\"2018-01-01T14:56:00.000+00:00\",\"customerId\":10002,\"quantity\":5,\"productCode\":\"PRODUCT_004\",\"cost\":250,\"location\":\"Australia\"},{\"transactionTime\":\"2018-01-01T14:56:00.000+00:00\",\"customerId\":10002,\"quantity\":5,\"productCode\":\"PRODUCT_001\",\"cost\":250,\"location\":\"Australia\"},{\"transactionTime\":\"2018-01-01T14:56:00.000+00:00\",\"customerId\":10002,\"quantity\":5,\"productCode\":\"PRODUCT_004\",\"cost\":250,\"location\":\"Australia\"},{\"transactionTime\":\"2018-01-01T14:56:00.000+00:00\",\"customerId\":10001,\"quantity\":5,\"productCode\":\"PRODUCT_001\",\"cost\":250,\"location\":\"Australia\"},{\"transactionTime\":\"2018-01-01T14:56:00.000+00:00\",\"customerId\":10003,\"quantity\":5,\"productCode\":\"PRODUCT_004\",\"cost\":250,\"location\":\"Australia\"},{\"transactionTime\":\"2018-01-01T14:56:00.000+00:00\",\"customerId\":10005,\"quantity\":5,\"productCode\":\"PRODUCT_002\",\"cost\":250,\"location\":\"Australia\"},{\"transactionTime\":\"2018-01-01T14:56:00.000+00:00\",\"customerId\":10004,\"quantity\":5,\"productCode\":\"PRODUCT_005\",\"cost\":250,\"location\":\"Australia\"},{\"transactionTime\":\"2018-01-01T14:56:00.000+00:00\",\"customerId\":10004,\"quantity\":5,\"productCode\":\"PRODUCT_003\",\"cost\":250,\"location\":\"Australia\"}]}"));
    }


    @Test
    public void createTransaction() throws Exception {
        RequestDto requestDto = new RequestDto();
        requestDto.setTransactionTime(getDate("11-11-2012 12:24"));
        requestDto.setProductCode("PRODUCT_001");
        requestDto.setCustomerId(10001);
        requestDto.setQuantity(4);

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/transactions")
                        .content(asJsonString(requestDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("Transaction Successful"));
    }

    @Test
    public void createTransaction_failIncorrectProductCode() throws Exception {
        RequestDto requestDto = new RequestDto();
        requestDto.setTransactionTime(getDate("11-11-2012 12:24"));
        requestDto.setProductCode("PRODUCT_1001");
        requestDto.setCustomerId(10001);
        requestDto.setQuantity(4);

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/transactions")
                        .content(asJsonString(requestDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createTransaction_failIncorrectCustomerId() throws Exception {
        RequestDto requestDto = new RequestDto();
        requestDto.setTransactionTime(getDate("11-11-2012 12:24"));
        requestDto.setProductCode("PRODUCT_001");
        requestDto.setCustomerId(1000);
        requestDto.setQuantity(4);

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/transactions")
                        .content(asJsonString(requestDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createTransaction_failFutureDate() throws Exception {
        RequestDto requestDto = new RequestDto();
        requestDto.setTransactionTime(getDate("11-11-2055 12:24"));
        requestDto.setProductCode("PRODUCT_001");
        requestDto.setCustomerId(10001);
        requestDto.setQuantity(4);

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/transactions")
                        .content(asJsonString(requestDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql({"/data_setup01.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = {"/delete_transactions_table.sql"})
    public void createTransaction_failProductInactive() throws Exception {
        RequestDto requestDto = new RequestDto();
        requestDto.setTransactionTime(getDate("11-11-2055 12:24"));
        requestDto.setProductCode("PRODUCT_002"); //inactive product
        requestDto.setCustomerId(10001);
        requestDto.setQuantity(4);

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/transactions")
                        .content(asJsonString(requestDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createTransaction_failtotalCostMoreThan5000() throws Exception {
        RequestDto requestDto = new RequestDto();
        requestDto.setTransactionTime(getDate("11-11-2023 12:24"));
        requestDto.setProductCode("PRODUCT_001");
        requestDto.setCustomerId(10001);
        requestDto.setQuantity(500);

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/transactions")
                        .content(asJsonString(requestDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


    private String asJsonString(final Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Date getDate(String inputString) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy ");
        Date inputDate;
        try {
            inputDate = dateFormat.parse(inputString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return inputDate;
    }

}