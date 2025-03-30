package org.gaurav.simpleapi.service;

import org.gaurav.simpleapi.model.dto.CustomerDto;
import org.gaurav.simpleapi.model.entity.Customer;
import org.gaurav.simpleapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomerServiceImpl {

    @Autowired
    CustomerRepository repository;

    public CustomerDto getCustomer(int id) {

        Optional<Customer> customer =  repository.findById(id);
        return customer.map(value -> new CustomerDto(value.getFirstName(),
                value.getLastName(),
                value.getEmail(),
                value.getLocation()))
                .orElse(null);
    }
}
