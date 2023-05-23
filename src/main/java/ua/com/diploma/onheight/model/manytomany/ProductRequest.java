package ua.com.diploma.onheight.model.manytomany;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.com.diploma.onheight.model.manytomany.utility.ProductRequestKey;
import ua.com.diploma.onheight.model.product.Product;
import ua.com.diploma.onheight.model.request.Request;

@Data
@NoArgsConstructor
@Entity
@Table(name = "products_requests")
public class ProductRequest {

    @EmbeddedId
    private ProductRequestKey id;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id", nullable = false)
    Product product;

    @ManyToOne
    @MapsId("requestId")
    @JoinColumn(name = "request_id", nullable = false)
    Request request;

    @Column(name = "product_qty", nullable = false)
    Integer productQty;

    public ProductRequest(ProductRequestKey id, Product product, Request request, Integer productQty) {
        this.id = id;
        this.product = product;
        this.request = request;
        this.productQty = productQty;
    }
}
