package ua.com.diploma.onheight.service;

import ua.com.diploma.onheight.model.warehouse.Address;

import java.util.List;

public interface AddressService extends CrudService<Address, Long> {

    List<Address> findAllByCountryAndCity(String country, String city);

    List<Address> findAllByAddress(String address);

    Address findByCountryAndCityAndAddressAndZipCode(String country, String city, String address, String zipCode);

}
