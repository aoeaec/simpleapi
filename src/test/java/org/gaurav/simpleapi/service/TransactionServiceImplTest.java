package org.gaurav.simpleapi.service;

import jakarta.validation.ValidationException;
import org.gaurav.simpleapi.model.StatusType;
import org.gaurav.simpleapi.model.dto.CustomerDto;
import org.gaurav.simpleapi.model.dto.ProductDto;
import org.gaurav.simpleapi.model.dto.RequestDto;
import org.gaurav.simpleapi.model.dto.TransactionResponseDto;
import org.gaurav.simpleapi.model.entity.Transaction;
import org.gaurav.simpleapi.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
class TransactionServiceImplTest {

    @Mock
    CustomerServiceImpl customerService;

    @Mock
    ProductServiceImpl productService;

    @Spy
    TransactionRepository transactionRepository;

    @InjectMocks
    TransactionServiceImpl transactionService;

    @Test
    void processTransaction_success() {
        RequestDto requestDto = new RequestDto();
        requestDto.setTransactionTime(new Date());
        requestDto.setCustomerId(10001);
        requestDto.setProductCode("PRODUCT_001");
        requestDto.setQuantity(5);

        when(customerService.getCustomer(10001)).thenReturn(new CustomerDto("firstName", "lastName", "email@email.com", "Australia"));
        when(productService.getProduct("PRODUCT_001")).thenReturn(new ProductDto("PRODUCT_001", 20, StatusType.Active));
        when(transactionRepository.save(any())).thenReturn(new Transaction());
        transactionService.processTransaction(requestDto);
    }


    @Test
    void processTransaction_failEmptyCustomer() {
        RequestDto requestDto = new RequestDto();
        requestDto.setTransactionTime(new Date());
        requestDto.setCustomerId(10001);
        requestDto.setProductCode("PRODUCT_001");
        requestDto.setQuantity(5);

        when(customerService.getCustomer(10001)).thenReturn(null);
        when(productService.getProduct("PRODUCT_001")).thenReturn(new ProductDto("PRODUCT_001", 20, StatusType.Active));
        when(transactionRepository.save(any())).thenReturn(new Transaction());
        assertThrows(ValidationException.class, () -> transactionService.processTransaction(requestDto));
    }

    @Test
    void processTransaction_failEmptyProduct() {
        RequestDto requestDto = new RequestDto();
        requestDto.setTransactionTime(new Date());
        requestDto.setCustomerId(10001);
        requestDto.setProductCode("PRODUCT_001");
        requestDto.setQuantity(5);

        when(customerService.getCustomer(10001)).thenReturn(new CustomerDto("firstName", "lastName", "email@email.com", "Australia"));
        when(productService.getProduct("PRODUCT_001")).thenReturn(null);
        when(transactionRepository.save(any())).thenReturn(new Transaction());
        assertThrows(ValidationException.class, () -> transactionService.processTransaction(requestDto));
    }


    @Test
    void getTransactionsForCustomerId() {

        List<Transaction> transations = getListOfTransactions();
        when(transactionRepository.findByCustomerId(1001)).thenReturn( transations.stream().filter(transaction -> transaction.getCustomerId().equals(1001)).collect(Collectors.toList()));
        TransactionResponseDto transactionResponseDto = transactionService.getTransactionsForCustomerId(1001);
        assertEquals(transactionResponseDto.getTransactionsList().size(), 5);
        assertEquals(transactionResponseDto.getTotalCost(), 325);

    }



    @Test
    void getTransactionsForProductId() {
        List<Transaction> transations = getListOfTransactions();
        when(transactionRepository.findByProductCode("PRODUCT_001")).thenReturn( transations.stream().filter(transaction -> transaction.getProductCode().equals("PRODUCT_001")).collect(Collectors.toList()));
        TransactionResponseDto transactionResponseDto = transactionService.getTransactionsForProductCode("PRODUCT_001");
        assertEquals(transactionResponseDto.getTransactionsList().size(), 11);
        assertEquals(transactionResponseDto.getTotalCost(), 543);
    }

    @Test
    void getTransactionsForLocation() {
        List<Transaction> transations = getListOfTransactions();
        when(transactionRepository.findByLocation("location")).thenReturn( transations.stream().filter(transaction -> transaction.getLocation().equals("Australia")).collect(Collectors.toList()));
        TransactionResponseDto transactionResponseDto = transactionService.getTransactionsForLocation("location");
        assertEquals(transactionResponseDto.getTransactionsList().size(), 10);
        assertEquals(transactionResponseDto.getTotalCost(), 689);
    }




    private List<Transaction> getListOfTransactions() {
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(getTransaction(1, 10, 1001, "PRODUCT_001", "Australia"));
        transactionList.add(getTransaction(2, 20, 1002, "PRODUCT_001", "US"));
        transactionList.add(getTransaction(3, 30, 1003, "PRODUCT_004", "Australia"));
        transactionList.add(getTransaction(4, 40, 1004, "PRODUCT_001", "US"));
        transactionList.add(getTransaction(5, 50, 1005, "PRODUCT_003", "US"));
        transactionList.add(getTransaction(6, 60, 1001, "PRODUCT_001", "Australia"));
        transactionList.add(getTransaction(7, 170, 1002, "PRODUCT_002", "Australia"));
        transactionList.add(getTransaction(8, 80, 1003, "PRODUCT_001", "US"));
        transactionList.add(getTransaction(9, 200, 1004, "PRODUCT_001", "US"));
        transactionList.add(getTransaction(10, 90, 1005, "PRODUCT_005", "Australia"));
        transactionList.add(getTransaction(11, 200, 1001, "PRODUCT_005", "Australia"));
        transactionList.add(getTransaction(12, 46, 1002, "PRODUCT_005", "Australia"));
        transactionList.add(getTransaction(13, 19, 1003, "PRODUCT_001", "Australia"));
        transactionList.add(getTransaction(14, 45, 1004, "PRODUCT_001", "US"));
        transactionList.add(getTransaction(15, 37, 1005, "PRODUCT_004", "US"));
        transactionList.add(getTransaction(16, 35, 1001, "PRODUCT_004", "Australia"));
        transactionList.add(getTransaction(17, 29, 1002, "PRODUCT_001", "Australia"));
        transactionList.add(getTransaction(18, 20, 1003, "PRODUCT_001", "US"));
        transactionList.add(getTransaction(19, 71, 1004, "PRODUCT_003", "US"));
        transactionList.add(getTransaction(20, 1000, 1005, "PRODUCT_002", "US"));
        transactionList.add(getTransaction(21, 20, 1001, "PRODUCT_001", "US"));
        return transactionList;
    }

    private Transaction getTransaction(int transactionId, int cost, int customerId, String productCode, String location) {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(transactionId);
        transaction.setTransactionTime(new Date());
        transaction.setCost(cost);
        transaction.setCustomerId(customerId);
        transaction.setProductCode(productCode);
        transaction.setLocation(location);
        return transaction;
    }

}