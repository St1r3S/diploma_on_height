package ua.com.diploma.onheight.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ua.com.diploma.onheight.model.company.Company;
import ua.com.diploma.onheight.model.manytomany.ProductRequest;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name", nullable = false)
    @NotEmpty(message = "{NotEmpty.Entity.Field}")
    @Size(min = 3, message = "{Size.User.FirstName}")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotEmpty(message = "{NotEmpty.Entity.Field}")
    @Size(min = 3, message = "{Size.User.LastName}")
    private String lastName;

    @Column(name = "email", unique = true, nullable = false)
    @NotEmpty(message = "{NotEmpty.Entity.Field}")
    @Email(message = "{Pattern.User.Email}")
    private String email;

    @Column(name = "phone_number", unique = true, nullable = false)
    @NotEmpty(message = "{NotEmpty.Entity.Field}")
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(name = "delivery_method", nullable = false)
    private DeliveryMethod deliveryMethod;

    @Column(name = "delivery_address", nullable = false)
    @NotEmpty(message = "{NotEmpty.Entity.Field}")
    private String deliveryAddress;

    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;

    @Column(name = "comment")
    private String comment;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OneToOne(mappedBy = "request")
    private CompletionStage completionStage;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "request")
    private List<ProductRequest> productRequests;

    public Request(Long id, String firstName, String lastName, String email, String phoneNumber, Company company, DeliveryMethod deliveryMethod, String deliveryAddress, PaymentMethod paymentMethod, String comment) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.company = company;
        this.deliveryMethod = deliveryMethod;
        this.deliveryAddress = deliveryAddress;
        this.paymentMethod = paymentMethod;
        this.comment = comment;
    }

    public Request(String firstName, String lastName, String email, String phoneNumber, Company company, DeliveryMethod deliveryMethod, String deliveryAddress, PaymentMethod paymentMethod, String comment) {
        this(null, firstName, lastName, email, phoneNumber, company, deliveryMethod, deliveryAddress, paymentMethod, comment);
    }
}
