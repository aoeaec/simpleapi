package org.gaurav.simpleapi.service;


import jakarta.validation.ValidationException;
import org.gaurav.simpleapi.model.dto.*;
import org.gaurav.simpleapi.model.entity.Transaction;
import org.gaurav.simpleapi.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    ProductService productService;
    @Override
    public void processTransaction(RequestDto requestDto){

        CustomerDto customer = customerService.getCustomer(requestDto.getCustomerId());
        ProductDto product = productService.getProduct(requestDto.getProductCode());

        if (Objects.isNull(customer) || Objects.isNull(product)) {
            throw new ValidationException("Invalid Customer or Product code");
        }

        TransactionDto transactionDto = new TransactionDto(requestDto.getTransactionTime(),
                requestDto.getCustomerId(),
                requestDto.getQuantity(),
                requestDto.getProductCode(),
                product.cost()*requestDto.getQuantity(),
                customer.location());
        Transaction transaction = convertToEntity(transactionDto);
        transaction.setProductStatus(product.statusType());
        transactionRepository.save(transaction);
    }

    @Override
    public TransactionResponseDto getTransactionsForCustomerId(Integer customerId) {
        List<Transaction> transactions = transactionRepository.findByCustomerId(customerId);
        return getTransactionResponseDto(transactions);
    }

    @Override
    public TransactionResponseDto getTransactionsForProductCode(String productCode) {
        List<Transaction> transactions = transactionRepository.findByProductCode(productCode);
        return getTransactionResponseDto(transactions);
    }

    @Override
    public TransactionResponseDto getTransactionsForLocation(String location) {
        List<Transaction> transactions = transactionRepository.findByLocation(location);
        return getTransactionResponseDto(transactions);
    }

    private TransactionResponseDto getTransactionResponseDto(List<Transaction> transactions) {
        TransactionResponseDto transactionResponseDto = new TransactionResponseDto();
        transactions.forEach(transaction -> transactionResponseDto.getTransactionsList().add(convertToDto(transaction)));
        transactionResponseDto.setTotalCost(transactionResponseDto.getTransactionsList().stream().mapToInt(TransactionDto::cost).sum());
        return transactionResponseDto;
    }


    private Transaction convertToEntity(TransactionDto transactionDto){
        Transaction transaction = new Transaction();
        transaction.setTransactionTime(transactionDto.transactionTime());
        transaction.setCustomerId(transactionDto.customerId());
        transaction.setProductCode(transactionDto.productCode());
        transaction.setQuantity(transactionDto.quantity());
        transaction.setCost(transactionDto.cost());
        transaction.setLocation(transactionDto.location());

        return transaction;
    }

    private TransactionDto convertToDto(Transaction transaction){
        return new TransactionDto(transaction.getTransactionTime(),
                transaction.getCustomerId(),
                transaction.getQuantity(),
                transaction.getProductCode(),
                transaction.getCost(),
                transaction.getLocation());
    }


}
