package org.gaurav.simpleapi.service;

import org.gaurav.simpleapi.model.dto.ProductDto;
import org.gaurav.simpleapi.model.entity.Product;
import org.gaurav.simpleapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl {
    @Autowired
    ProductRepository repository;

    public ProductDto getProduct(String id) {
        Optional<Product> product =  repository.findById(id);
        return product.map(value -> new ProductDto(value.getProductCode()
                ,value.getCost()
                ,value.getStatusType()))
                .orElse(null);

    }
}
