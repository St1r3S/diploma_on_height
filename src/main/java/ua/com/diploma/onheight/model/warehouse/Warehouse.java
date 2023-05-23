package ua.com.diploma.onheight.model.warehouse;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ua.com.diploma.onheight.model.company.Company;
import ua.com.diploma.onheight.model.product.Product;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "warehouses")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "classification", nullable = false)
    private WarehouseClassification classification;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Column(name = "phone_number", unique = true, nullable = false)
    @NotEmpty(message = "{NotEmpty.Entity.Field}")
    private String phoneNumber;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "warehouse")
    private List<Product> products;

    public Warehouse(Long id, WarehouseClassification classification, String description, Company company, Address address, String phoneNumber) {
        this.id = id;
        this.classification = classification;
        this.description = description;
        this.company = company;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Warehouse(WarehouseClassification classification, String description, Company company, Address address, String phoneNumber) {
        this(null, classification, description, company, address, phoneNumber);
    }
}
