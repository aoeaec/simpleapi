package org.gaurav.simpleapi.model.dto;

import org.gaurav.simpleapi.model.StatusType;


public record ProductDto(String productCode,
                         int cost,
                         StatusType statusType) { }
