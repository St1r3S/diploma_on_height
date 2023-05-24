package ua.com.diploma.onheight.service.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.diploma.onheight.model.warehouse.Address;
import ua.com.diploma.onheight.repository.AddressRepository;
import ua.com.diploma.onheight.service.AddressService;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    @Transactional
    public Address save(Address entity) {
        try {
            return addressRepository.save(entity);
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to save entity " + entity, 1);
        }
    }

    @Override
    @Transactional
    public List<Address> saveAll(List<Address> entities) {
        try {
            return addressRepository.saveAll(entities);
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to save entities " + entities, 1);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Address findById(Long id) {
        return addressRepository.findById(id).orElseThrow(
                () -> new EmptyResultDataAccessException("There's no such address with id " + id, 1));
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return addressRepository.existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Address> findAllById(List<Long> ids) {
        return addressRepository.findAllById(ids);
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return addressRepository.count();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        try {
            addressRepository.deleteById(id);
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to delete entity with id " + id, 1);
        }
    }

    @Override
    @Transactional
    public void delete(Address entity) {
        try {
            addressRepository.deleteById(entity.getId());
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to delete entity " + entity, 1);
        }
    }

    @Override
    @Transactional
    public void deleteAllById(List<Long> ids) {
        try {
            addressRepository.deleteAllById(ids);
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to delete entities with ids " + ids, 1);
        }
    }

    @Override
    @Transactional
    public void deleteAll(List<Address> entities) {
        try {
            addressRepository.deleteAll(entities);
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to delete entities " + entities, 1);
        }
    }

    @Override
    @Transactional
    public void deleteAll() {
        try {
            addressRepository.deleteAll();
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to delete all entities ", 1);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Address> findAllByCountryAndCity(String country, String city) {
        return addressRepository.findAllByCountryAndCity(country, city);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Address> findAllByAddress(String address) {
        return addressRepository.findAllByAddress(address);
    }
}
