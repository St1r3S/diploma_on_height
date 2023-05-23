package ua.com.diploma.onheight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.diploma.onheight.model.product.Product;
import ua.com.diploma.onheight.model.product.ProductType;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByProductType(ProductType productType);

    List<Product> findAllByCompanyId(Long companyId);

    Optional<Product> findByProductTypeAndProductNameAndWarehouseIdAndCompanyId(ProductType productType, String productName, Long warehouseId, Long companyId);
}
