package ua.com.diploma.onheight.model.warehouse;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "country", nullable = false)
    @NotEmpty(message = "{NotEmpty.Entity.Field}")
    private String country;

    @Column(name = "city", nullable = false)
    @NotEmpty(message = "{NotEmpty.Entity.Field}")
    private String city;

    @Column(name = "address", nullable = false)
    @NotEmpty(message = "{NotEmpty.Entity.Field}")
    private String address;

    @Column(name = "zip_or_postcode", nullable = false)
    @NotEmpty(message = "{NotEmpty.Entity.Field}")
    private String zipOrPostcode;

    @Column(name = "last_update", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "{PastOrPresent.Entity.Date}")
    private LocalDate lastUpdate;

    public Address(Long id, String country, String city, String address, String zipOrPostcode, LocalDate lastUpdate) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.address = address;
        this.zipOrPostcode = zipOrPostcode;
        this.lastUpdate = lastUpdate;
    }

    public Address(String country, String city, String address, String zipOrPostcode, LocalDate lastUpdate) {
        this(null, country, city, address, zipOrPostcode, lastUpdate);
    }
}
