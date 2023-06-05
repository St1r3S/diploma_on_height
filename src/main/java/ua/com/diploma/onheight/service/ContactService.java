package ua.com.diploma.onheight.service;

import ua.com.diploma.onheight.model.company.Contact;

import java.util.List;

public interface ContactService extends CrudService<Contact, Long> {

    List<Contact> findAllByCompanyId(Long companyId);

    List<Contact> findAllByCompanyIdAndLastName(Long companyId, String lastName);

    List<Contact> findAllByCompanyIdAndEmail(Long companyId, String email);

    Contact findByEmail(String email);

    Contact findByPhoneNumber(String phoneNumber);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);
}
