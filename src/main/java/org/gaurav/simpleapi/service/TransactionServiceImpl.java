package org.gaurav.simpleapi.service;


import jakarta.validation.ValidationException;
import org.gaurav.simpleapi.model.dto.*;
import org.gaurav.simpleapi.model.entity.Customer;
import org.gaurav.simpleapi.model.entity.Transaction;
import org.gaurav.simpleapi.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TransactionServiceImpl {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    CustomerServiceImpl customerService;

    @Autowired
    ProductServiceImpl productService;
    public void processTransaction(RequestDto requestDto){

        Customer customer = customerService.getCustomer(requestDto.getCustomerId());
        ProductDto product = productService.getProduct(requestDto.getProductCode());

        if (Objects.isNull(customer) || Objects.isNull(product)) {
            throw new ValidationException("Invalid Customer or Product code");
        }

        TransactionDto transactionDto = new TransactionDto(requestDto.getTransactionTime(),
                requestDto.getCustomerId(),
                requestDto.getQuantity(),
                requestDto.getProductCode(),
                product.cost()*requestDto.getQuantity() );
        Transaction transaction = convertToEntity(transactionDto, customer);
        transaction.setProductStatus(product.statusType());
        transactionRepository.save(transaction);
    }

    public TransactionResponseDto getTransactionsForCustomerId(Integer customerId) {
        Customer customer = customerService.getCustomer(customerId);
        List<Transaction> transactions = transactionRepository.findByCustomer(customer);
        return getTransactionResponseDto(transactions);
    }

    public TransactionResponseDto getTransactionsForProductId(String productCode) {
        List<Transaction> transactions = transactionRepository.findByProductCode(productCode);
        return getTransactionResponseDto(transactions);
    }

    public TransactionResponseDto getTransactionsForLocation(String location) {
        //List<Transaction> transactions = transactionRepository.findByLocation(location);
        //return getTransactionResponseDto(transactions);
        return null;
    }

    private TransactionResponseDto getTransactionResponseDto(List<Transaction> transactions) {
        TransactionResponseDto transactionResponseDto = new TransactionResponseDto();
        transactions.forEach(transaction -> transactionResponseDto.getTransactionsList().add(convertToDto(transaction)));
        transactionResponseDto.setTotalCost(transactionResponseDto.getTransactionsList().stream().mapToInt(TransactionDto::cost).sum());
        return transactionResponseDto;
    }


    private Transaction convertToEntity(TransactionDto transactionDto, Customer customer){
        Transaction transaction = new Transaction();
        transaction.setCustomer(customer);
        transaction.setProductCode(transactionDto.productCode());
        transaction.setQuantity(transactionDto.quantity());
        transaction.setCost(transactionDto.cost());

        return transaction;
    }

    private TransactionDto convertToDto(Transaction transaction){
        return new TransactionDto(transaction.getTransactionTime(),
                transaction.getCustomer().getId(),
                transaction.getQuantity(),
                transaction.getProductCode(),
                transaction.getCost());
    }


}
