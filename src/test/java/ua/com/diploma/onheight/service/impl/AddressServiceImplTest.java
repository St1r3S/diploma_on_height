package ua.com.diploma.onheight.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import ua.com.diploma.onheight.model.warehouse.Address;
import ua.com.diploma.onheight.repository.AddressRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {AddressServiceImpl.class})
class AddressServiceImplTest {
    @Autowired
    AddressServiceImpl addressService;
    @MockBean
    AddressRepository addressRepository;

    @Test
    void shouldThrowNotFindException() {
        assertThrows(EmptyResultDataAccessException.class, () -> addressService.findById(6L));
    }

    @Test
    void shouldVerifyFindAllByCountryAndCity() {
        String country = "USA";
        String city = "New York";
        Address address1 = new Address(1L, country, city, "123 Main Street", "12345", LocalDate.now());
        Address address2 = new Address(2L, country, city, "456 Elm Street", "67890", LocalDate.now());
        List<Address> addresses = List.of(address1, address2);

        when(addressRepository.findAllByCountryAndCity(country, city)).thenReturn(addresses);
        List<Address> actual = addressService.findAllByCountryAndCity(country, city);

        assertEquals(addresses, actual);
    }

    @Test
    void shouldVerifyFindAllByAddress() {
        String addressString = "123 Main Street";
        Address address1 = new Address(1L, "USA", "New York", addressString, "12345", LocalDate.now());
        Address address2 = new Address(2L, "USA", "Los Angeles", addressString, "67890", LocalDate.now());
        List<Address> addresses = List.of(address1, address2);

        when(addressRepository.findAllByAddress(addressString)).thenReturn(addresses);
        List<Address> actual = addressService.findAllByAddress(addressString);

        assertEquals(addresses, actual);
    }

    @Test
    void shouldVerifyFindByCountryAndCityAndAddressAndZipCode() {
        String country = "USA";
        String city = "New York";
        String addressString = "123 Main Street";
        String zipCode = "12345";
        Address expected = new Address(1L, country, city, addressString, zipCode, LocalDate.now());

        when(addressRepository.findByCountryAndCityAndAddressAndZipCode(country, city, addressString, zipCode)).thenReturn(Optional.of(expected));
        Address actual = addressService.findByCountryAndCityAndAddressAndZipCode(country, city, addressString, zipCode);

        assertEquals(expected, actual);
    }
}

