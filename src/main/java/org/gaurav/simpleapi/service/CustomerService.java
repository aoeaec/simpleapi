package org.gaurav.simpleapi.service;

import org.gaurav.simpleapi.model.dto.CustomerDto;

public interface CustomerService {
    CustomerDto getCustomer(int id);
}
