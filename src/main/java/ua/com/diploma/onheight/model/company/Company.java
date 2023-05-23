package ua.com.diploma.onheight.model.company;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ua.com.diploma.onheight.model.request.Request;
import ua.com.diploma.onheight.model.user.User;
import ua.com.diploma.onheight.model.warehouse.Warehouse;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "company_name", unique = true, nullable = false)
    @NotEmpty(message = "{NotEmpty.Entity.Field}")
    private String companyName;

    @Column(name = "corporate_email", unique = true, nullable = false)
    @NotEmpty(message = "{NotEmpty.Entity.Field}")
    @Email(message = "{Pattern.Entity.Email}")
    private String corporateEmail;

    @Column(name = "corporate_phone", unique = true, nullable = false)
    @NotEmpty(message = "{NotEmpty.Entity.Field}")
    private String corporatePhone;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<User> users;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<Warehouse> warehouses;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<Request> requests;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<Contact> contacts;

    public Company(Long id, String companyName, String corporateEmail, String corporatePhone) {
        this.id = id;
        this.companyName = companyName;
        this.corporateEmail = corporateEmail;
        this.corporatePhone = corporatePhone;
    }

    public Company(String companyName, String corporateEmail, String corporatePhone) {
        this(null, companyName, corporateEmail, corporatePhone);
    }
}
