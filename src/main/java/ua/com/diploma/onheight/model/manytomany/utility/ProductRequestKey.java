package ua.com.diploma.onheight.model.manytomany.utility;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
public class ProductRequestKey implements Serializable {
    
    @Column(name = "product_id")
    Long productId;

    @Column(name = "request_id")
    Long requestId;

    public ProductRequestKey(Long productId, Long requestId) {
        this.productId = productId;
        this.requestId = requestId;
    }
}
