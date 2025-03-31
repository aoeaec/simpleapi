package org.gaurav.simpleapi.service;

import org.gaurav.simpleapi.model.dto.ProductDto;

public interface ProductService {
    ProductDto getProduct(String id);
}
