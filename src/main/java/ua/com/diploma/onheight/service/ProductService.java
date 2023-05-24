package ua.com.diploma.onheight.service;

import ua.com.diploma.onheight.model.product.Product;
import ua.com.diploma.onheight.model.product.ProductType;

import java.util.List;

public interface ProductService extends CrudService<Product, Long> {

    List<Product> findAllByProductType(ProductType productType);

    List<Product> findAllByCompanyId(Long companyId);

    Product findByProductTypeAndProductNameAndWarehouseIdAndCompanyId(ProductType productType, String productName, Long warehouseId, Long companyId);
}
