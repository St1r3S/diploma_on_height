package ua.com.diploma.onheight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.com.diploma.onheight.model.manytomany.ProductRequest;
import ua.com.diploma.onheight.model.manytomany.utility.ProductRequestKey;

@Repository
public interface ProductRequestRepository extends JpaRepository<ProductRequest, ProductRequestKey> {

    @Query("SELECT SUM(pr.productQty) FROM ProductRequest pr WHERE pr.product.id = :productId")
    Integer findProductQtySumByProductId(@Param("productId") Long productId);
}
