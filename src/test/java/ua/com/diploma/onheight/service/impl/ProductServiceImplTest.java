package ua.com.diploma.onheight.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import ua.com.diploma.onheight.model.product.PriceCurrency;
import ua.com.diploma.onheight.model.product.Product;
import ua.com.diploma.onheight.model.product.ProductType;
import ua.com.diploma.onheight.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {ProductServiceImpl.class})
class ProductServiceImplTest {
    @Autowired
    ProductServiceImpl productService;
    @MockBean
    ProductRepository productRepository;

    @Test
    void shouldThrowNotFindException() {
        assertThrows(EmptyResultDataAccessException.class, () -> productService.findById(6L));
    }

    @Test
    void shouldVerifyFindAllByProductType() {
        Product product1 = new Product(1L, ProductType.TECHNOLOGY, "Laptop", 10, 1000, PriceCurrency.USD, null, null);
        Product product2 = new Product(2L, ProductType.TECHNOLOGY, "Smartphone", 20, 800, PriceCurrency.USD, null, null);
        List<Product> expected = List.of(product1, product2);

        when(productRepository.findAllByProductType(ProductType.TECHNOLOGY)).thenReturn(expected);
        List<Product> actual = productService.findAllByProductType(ProductType.TECHNOLOGY);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyFindAllByCompanyId() {
        Long companyId = 1L;
        Product product1 = new Product(1L, ProductType.TECHNOLOGY, "Laptop", 10, 1000, PriceCurrency.USD, null, null);
        Product product2 = new Product(2L, ProductType.HOMEMADE_CLOTHING, "T-Shirt", 20, 30, PriceCurrency.USD, null, null);
        List<Product> expected = List.of(product1, product2);

        when(productRepository.findAllByCompanyId(companyId)).thenReturn(expected);
        List<Product> actual = productService.findAllByCompanyId(companyId);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyFindAllByWarehouseId() {
        Long warehouseId = 1L;
        Product product1 = new Product(1L, ProductType.TECHNOLOGY, "Laptop", 10, 1000, PriceCurrency.USD, null, null);
        Product product2 = new Product(2L, ProductType.HOMEMADE_CLOTHING, "T-Shirt", 20, 30, PriceCurrency.USD, null, null);
        List<Product> expected = List.of(product1, product2);

        when(productRepository.findAllByWarehouseId(warehouseId)).thenReturn(expected);
        List<Product> actual = productService.findAllByWarehouseId(warehouseId);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyFindAllByWarehouseIdAndProductType() {
        Long warehouseId = 1L;
        ProductType productType = ProductType.TECHNOLOGY;
        Product product1 = new Product(1L, ProductType.TECHNOLOGY, "Laptop", 10, 1000, PriceCurrency.USD, null, null);
        Product product2 = new Product(2L, ProductType.TECHNOLOGY, "Smartphone", 20, 800, PriceCurrency.USD, null, null);
        List<Product> expected = List.of(product1, product2);

        when(productRepository.findAllByWarehouseIdAndProductType(warehouseId, productType)).thenReturn(expected);
        List<Product> actual = productService.findAllByWarehouseIdAndProductType(warehouseId, productType);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyFindAllByWarehouseIdAndProductName() {
        Long warehouseId = 1L;
        String productName = "Laptop";
        Product product1 = new Product(1L, ProductType.TECHNOLOGY, "Laptop", 10, 1000, PriceCurrency.USD, null, null);
        List<Product> expected = List.of(product1);

        when(productRepository.findAllByWarehouseIdAndProductName(warehouseId, productName)).thenReturn(expected);
        List<Product> actual = productService.findAllByWarehouseIdAndProductName(warehouseId, productName);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyFindByProductTypeAndProductNameAndWarehouseIdAndCompanyId() {
        ProductType productType = ProductType.TECHNOLOGY;
        String productName = "Laptop";
        Long warehouseId = 1L;
        Long companyId = 1L;
        Product product1 = new Product(1L, ProductType.TECHNOLOGY, "Laptop", 10, 1000, PriceCurrency.USD, null, null);

        when(productRepository.findByProductTypeAndProductNameAndWarehouseIdAndCompanyId(productType, productName, warehouseId, companyId)).thenReturn(Optional.of(product1));
        Product actual = productService.findByProductTypeAndProductNameAndWarehouseIdAndCompanyId(productType, productName, warehouseId, companyId);

        assertEquals(product1, actual);
    }
}

