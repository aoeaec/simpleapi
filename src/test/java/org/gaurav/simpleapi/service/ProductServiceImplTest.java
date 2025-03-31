package org.gaurav.simpleapi.service;

import org.gaurav.simpleapi.model.StatusType;
import org.gaurav.simpleapi.model.dto.ProductDto;
import org.gaurav.simpleapi.model.entity.Product;
import org.gaurav.simpleapi.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    ProductServiceImpl productService;

    @Spy
    ProductRepository repository;
    @Test
    void getProduct() {

        when(repository.findById("PRODUCT_001")).thenReturn(Optional.of(getProduct(10, "PRODUCT_001")));
        ProductDto productDto = productService.getProduct("PRODUCT_001");
        assertEquals(productDto.productCode(), "PRODUCT_001");
        assertEquals(productDto.cost(), 10);
        assertEquals(productDto.statusType(), StatusType.Inactive);
    }



    private Product getProduct(int cost, String productCode) {
        Product product = new Product();
        product.setProductCode(productCode);
        product.setCost(cost);
        product.setStatusType(StatusType.Inactive);
        return product;
    }
}