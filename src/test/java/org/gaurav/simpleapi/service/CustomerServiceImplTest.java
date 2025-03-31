package org.gaurav.simpleapi.service;

import org.gaurav.simpleapi.model.dto.CustomerDto;
import org.gaurav.simpleapi.model.entity.Customer;
import org.gaurav.simpleapi.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CustomerServiceImplTest {

    @InjectMocks
    CustomerServiceImpl customerService;

    @Spy
    CustomerRepository repository;
    @Test
    void getCustomer() {

        when(repository.findById(1001)).thenReturn(Optional.of(getCustomer("firstName", "lastName", "email@email.com", "Australia")));
        CustomerDto customerDto = customerService.getCustomer(1001);
        assertEquals(customerDto.firstName(), "firstName");
        assertEquals(customerDto.lastName(), "lastName");
        assertEquals(customerDto.email(), "email@email.com");
        assertEquals(customerDto.location(), "Australia");

    }



    private Customer getCustomer(String firstName, String lastName, String email, String location) {
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setLocation(location);
        return customer;
    }
}