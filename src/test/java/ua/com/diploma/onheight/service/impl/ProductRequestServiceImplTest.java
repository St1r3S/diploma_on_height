package ua.com.diploma.onheight.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import ua.com.diploma.onheight.model.manytomany.ProductRequest;
import ua.com.diploma.onheight.model.manytomany.utility.ProductRequestKey;
import ua.com.diploma.onheight.model.product.Product;
import ua.com.diploma.onheight.model.request.Request;
import ua.com.diploma.onheight.repository.ProductRequestRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {ProductRequestServiceImpl.class})
class ProductRequestServiceImplTest {
    @Autowired
    ProductRequestServiceImpl productRequestService;
    @MockBean
    ProductRequestRepository productRequestRepository;

    @Test
    void shouldThrowNotFindException() {
        ProductRequestKey id = new ProductRequestKey(1L, 1L);
        assertThrows(EmptyResultDataAccessException.class, () -> productRequestService.findById(id));
    }

    @Test
    void shouldVerifyFindAllByRequestId() {
        Long requestId = 1L;
        List<ProductRequest> productRequests = List.of(
                new ProductRequest(new ProductRequestKey(1L, 1L), new Product(), new Request(), 5),
                new ProductRequest(new ProductRequestKey(2L, 1L), new Product(), new Request(), 10)
        );
        List<ProductRequest> expected = List.of(
                new ProductRequest(new ProductRequestKey(1L, 1L), new Product(), new Request(), 5),
                new ProductRequest(new ProductRequestKey(2L, 1L), new Product(), new Request(), 10)
        );

        when(productRequestRepository.findAllByRequestId(requestId)).thenReturn(productRequests);
        List<ProductRequest> actual = productRequestService.findAllByRequestId(requestId);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyFindProductQtySumByProductId() {
        Long productId = 1L;
        Integer productQtySum = 15;

        when(productRequestRepository.findProductQtySumByProductId(productId)).thenReturn(productQtySum);
        Integer actual = productRequestService.findProductQtySumByProductId(productId);

        assertEquals(productQtySum, actual);
    }
}

