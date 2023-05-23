package ua.com.diploma.onheight.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ua.com.diploma.onheight.model.company.Company;
import ua.com.diploma.onheight.model.manytomany.ProductRequest;
import ua.com.diploma.onheight.model.warehouse.Warehouse;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_type", nullable = false)
    private ProductType productType;

    @Column(name = "product_name", nullable = false)
    @NotEmpty(message = "{NotEmpty.Entity.Field}")
    private String productName;

    @Column(name = "product_qty", nullable = false)
    private Integer productQty;

    @Column(name = "unit_price", nullable = false)
    private Integer unitPrice;

    @Column(name = "price_currency", nullable = false)
    private PriceCurrency priceCurrency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<ProductRequest> productRequests;

    public Product(Long id, ProductType productType, String productName, Integer productQty, Integer unitPrice, PriceCurrency priceCurrency, Warehouse warehouse, Company company) {
        this.id = id;
        this.productType = productType;
        this.productName = productName;
        this.productQty = productQty;
        this.unitPrice = unitPrice;
        this.priceCurrency = priceCurrency;
        this.warehouse = warehouse;
        this.company = company;
    }

    public Product(ProductType productType, String productName, Integer productQty, Integer unitPrice, PriceCurrency priceCurrency, Warehouse warehouse, Company company) {
        this(null, productType, productName, productQty, unitPrice, priceCurrency, warehouse, company);
    }
}
