package ua.com.diploma.onheight.model.company;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "contacts")
public class Contact {

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

    @Column(name = "contact_description")
    private String contactDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    public Contact(Long id, String firstName, String lastName, String email, String phoneNumber, String contactDescription, Company company) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.contactDescription = contactDescription;
        this.company = company;
    }

    public Contact(String firstName, String lastName, String email, String phoneNumber, String contactDescription, Company company) {
        this(null, firstName, lastName, email, phoneNumber, contactDescription, company);
    }
}
