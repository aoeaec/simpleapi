package org.gaurav.simpleapi.service;


import jakarta.validation.ValidationException;
import org.gaurav.simpleapi.model.dto.CustomerDto;
import org.gaurav.simpleapi.model.dto.ProductDto;
import org.gaurav.simpleapi.model.entity.Customer;
import org.gaurav.simpleapi.model.entity.Product;
import org.gaurav.simpleapi.model.entity.Transaction;
import org.gaurav.simpleapi.repository.CustomerRepository;
import org.gaurav.simpleapi.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Objects;
import java.util.Optional;

@Service
public class TransactionServiceImpl {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    CustomerServiceImpl customerService;

    @Autowired
    ProductServiceImpl productService;
    public void processTransaction(Transaction transactionRequest){
        CustomerDto customer = customerService.getCustomer(transactionRequest.getCustomerId());
        ProductDto product = productService.getProduct(transactionRequest.getProductCode());


        if(Objects.isNull(customer) || Objects.isNull(product)) {
            throw new ValidationException("Invalid Customer or Product code");
        }

        transactionRequest.setCost(product.cost()*transactionRequest.getQuantity());
        transactionRequest.setStatusType(product.statusType());
        transactionRepository.save(transactionRequest);

    }
}
