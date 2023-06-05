package ua.com.diploma.onheight.service.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.diploma.onheight.model.company.Contact;
import ua.com.diploma.onheight.repository.ContactRepository;
import ua.com.diploma.onheight.service.ContactService;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    @Transactional
    public Contact save(Contact entity) {
        try {
            return contactRepository.save(entity);
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to save entity " + entity, 1);
        }
    }

    @Override
    @Transactional
    public List<Contact> saveAll(List<Contact> entities) {
        try {
            return contactRepository.saveAll(entities);
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to save entities " + entities, 1);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Contact findById(Long id) {
        return contactRepository.findById(id).orElseThrow(
                () -> new EmptyResultDataAccessException("There's no such contact with id " + id, 1));
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return contactRepository.existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contact> findAllById(List<Long> ids) {
        return contactRepository.findAllById(ids);
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return contactRepository.count();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        try {
            contactRepository.deleteById(id);
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to delete entity with id " + id, 1);
        }
    }

    @Override
    @Transactional
    public void delete(Contact entity) {
        try {
            contactRepository.deleteById(entity.getId());
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to delete entity " + entity, 1);
        }
    }

    @Override
    @Transactional
    public void deleteAllById(List<Long> ids) {
        try {
            contactRepository.deleteAllById(ids);
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to delete entities with ids " + ids, 1);
        }
    }

    @Override
    @Transactional
    public void deleteAll(List<Contact> entities) {
        try {
            contactRepository.deleteAll(entities);
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to delete entities " + entities, 1);
        }
    }

    @Override
    @Transactional
    public void deleteAll() {
        try {
            contactRepository.deleteAll();
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to delete all entities ", 1);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contact> findAllByCompanyId(Long companyId) {
        return contactRepository.findAllByCompanyId(companyId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contact> findAllByCompanyIdAndLastName(Long companyId, String lastName) {
        return contactRepository.findAllByCompanyIdAndLastName(companyId, lastName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contact> findAllByCompanyIdAndEmail(Long companyId, String email) {
        return contactRepository.findAllByCompanyIdAndEmail(companyId, email);
    }

    @Override
    @Transactional(readOnly = true)
    public Contact findByEmail(String email) {
        return contactRepository.findByEmail(email).orElseThrow(
                () -> new EmptyResultDataAccessException("There's no such contact with email " + email, 1));
    }

    @Override
    @Transactional(readOnly = true)
    public Contact findByPhoneNumber(String phoneNumber) {
        return contactRepository.findByPhoneNumber(phoneNumber).orElseThrow(
                () -> new EmptyResultDataAccessException("There's no such contact with phoneNumber " + phoneNumber, 1));
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        try {
            findByEmail(email);
        } catch (EmptyResultDataAccessException ex) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByPhoneNumber(String phoneNumber) {
        try {
            findByPhoneNumber(phoneNumber);
        } catch (EmptyResultDataAccessException ex) {
            return false;
        }
        return true;
    }


}
