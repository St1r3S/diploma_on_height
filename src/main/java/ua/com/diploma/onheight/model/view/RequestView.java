package ua.com.diploma.onheight.model.view;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import ua.com.diploma.onheight.model.company.Company;
import ua.com.diploma.onheight.model.manytomany.ProductRequest;
import ua.com.diploma.onheight.model.request.CompletionStage;
import ua.com.diploma.onheight.model.request.DeliveryMethod;
import ua.com.diploma.onheight.model.request.PaymentMethod;
import ua.com.diploma.onheight.model.request.Request;

@EqualsAndHashCode
@Getter
@Setter
@ToString
@NoArgsConstructor
public class RequestView {

    private Long id;

    @NotEmpty(message = "{NotEmpty.Entity.Field}")
    @Size(min = 3, message = "{Size.User.FirstName}")
    private String firstName;

    @NotEmpty(message = "{NotEmpty.Entity.Field}")
    @Size(min = 3, message = "{Size.User.LastName}")
    private String lastName;

    @NotEmpty(message = "{NotEmpty.Entity.Field}")
    @Email(message = "{Pattern.User.Email}")
    private String email;

    @NotEmpty(message = "{NotEmpty.Entity.Field}")
    private String phoneNumber;

    private Company company;

    private DeliveryMethod deliveryMethod;

    @NotEmpty(message = "{NotEmpty.Entity.Field}")
    private String deliveryAddress;

    private PaymentMethod paymentMethod;

    private String comment;

    private CompletionStage completionStage;

    private ProductRequest productRequest;

    public RequestView(Long id, String firstName, String lastName, String email, String phoneNumber, Company company, DeliveryMethod deliveryMethod, String deliveryAddress, PaymentMethod paymentMethod, String comment, CompletionStage completionStage, ProductRequest productRequest) {
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
        this.completionStage = completionStage;
        this.productRequest = productRequest;
    }

    public static RequestView entityToView(Request request) {
        return new RequestView(
                request.getId(),
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPhoneNumber(),
                request.getCompany(),
                request.getDeliveryMethod(),
                request.getDeliveryAddress(),
                request.getPaymentMethod(),
                request.getComment(),
                request.getCompletionStage(),
                request.getProductRequests().get(0));
    }

    public Request viewToEntity() {
        return new Request(id, firstName, lastName, email, phoneNumber, company, deliveryMethod, deliveryAddress, paymentMethod, comment);
    }
}
