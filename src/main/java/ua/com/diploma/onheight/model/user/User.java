package ua.com.diploma.onheight.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import ua.com.diploma.onheight.model.company.Company;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_role", nullable = false)
    private UserRole userRole;

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

    @Column(name = "username", unique = true, nullable = false)
    @NotEmpty(message = "{NotEmpty.Entity.Field}")
    @Size(min = 5, message = "{Size.User.Username}")
    private String username;

    @Column(name = "password_hash", nullable = false)
    @NotEmpty(message = "{NotEmpty.Entity.Field}")
    @Size(min = 5, message = "{Size.User.Password}")
    private String passwordHash;

    @Column(name = "birthday", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "{Past.User.Birthday}")
    private LocalDate birthday;

    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "department", nullable = false)
    private Department department;

    @Column(name = "post", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    public User(Long id, UserRole userRole, String firstName, String lastName, String email, String phoneNumber, String username, String passwordHash, LocalDate birthday, Gender gender, Department department, Post post, Company company) {
        this.id = id;
        this.userRole = userRole;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.passwordHash = passwordHash;
        this.birthday = birthday;
        this.gender = gender;
        this.department = department;
        this.post = post;
        this.company = company;
    }

    public User(UserRole userRole, String firstName, String lastName, String email, String phoneNumber, String username, String passwordHash, LocalDate birthday, Gender gender, Department department, Post post, Company company) {
        this(null, userRole, firstName, lastName, email, phoneNumber, username, passwordHash, birthday, gender, department, post, company);
    }
}
