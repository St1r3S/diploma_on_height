package ua.com.diploma.onheight.service;

import ua.com.diploma.onheight.model.product.Product;
import ua.com.diploma.onheight.model.product.ProductType;

import java.util.List;

public interface ProductService extends CrudService<Product, Long> {

    List<Product> findAllByProductType(ProductType productType);

    List<Product> findAllByCompanyId(Long companyId);

    List<Product> findAllByWarehouseId(Long warehouseId);

    List<Product> findAllByWarehouseIdAndProductType(Long warehouseId, ProductType productType);

    List<Product> findAllByWarehouseIdAndProductName(Long warehouseId, String productName);

    Product findByProductTypeAndProductNameAndWarehouseIdAndCompanyId(ProductType productType, String productName, Long warehouseId, Long companyId);
}
