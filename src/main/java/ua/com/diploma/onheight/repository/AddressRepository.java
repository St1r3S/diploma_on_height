package ua.com.diploma.onheight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.diploma.onheight.model.warehouse.Address;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findAllByCountryAndCity(String country, String city);

    List<Address> findAllByAddress(String address);

    Optional<Address> findByCountryAndCityAndAddressAndZipCode(String country, String city, String address, String zipCode);
}
