package ua.com.diploma.onheight.service;

import ua.com.diploma.onheight.model.company.Contact;

import java.util.List;

public interface ContactService extends CrudService<Contact, Long> {

    List<Contact> findAllByCompanyId(Long companyId);
}
