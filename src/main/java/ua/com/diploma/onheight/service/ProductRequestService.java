package ua.com.diploma.onheight.service;

import ua.com.diploma.onheight.model.manytomany.ProductRequest;
import ua.com.diploma.onheight.model.manytomany.utility.ProductRequestKey;

import java.util.List;

public interface ProductRequestService extends CrudService<ProductRequest, ProductRequestKey> {
    List<ProductRequest> findAllByRequestId(Long requestId);

    Integer findProductQtySumByProductId(Long productId);
}
