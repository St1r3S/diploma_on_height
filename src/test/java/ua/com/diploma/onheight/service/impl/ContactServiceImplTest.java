package ua.com.diploma.onheight.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import ua.com.diploma.onheight.model.company.Company;
import ua.com.diploma.onheight.model.company.Contact;
import ua.com.diploma.onheight.repository.ContactRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {ContactServiceImpl.class})
class ContactServiceImplTest {
    @Autowired
    ContactServiceImpl contactService;
    @MockBean
    ContactRepository contactRepository;

    @Test
    void shouldThrowNotFindException() {
        assertThrows(EmptyResultDataAccessException.class, () -> contactService.findById(6L));
    }

    @Test
    void shouldVerifyFindAllByCompanyId() {
        Company company = new Company(1L, "Company 1", "abc@company.com", "1234567890");
        List<Contact> contacts = List.of(
                new Contact(1L, "John", "Doe", "john@example.com", "1234567890", "Description 1", company),
                new Contact(2L, "Jane", "Smith", "jane@example.com", "9876543210", "Description 2", company),
                new Contact(3L, "Alice", "Johnson", "alice@example.com", "4567891230", "Description 3", company)
        );
        List<Contact> expected = contacts.stream()
                .filter(contact -> contact.getCompany().getId().equals(company.getId()))
                .collect(Collectors.toList());

        when(contactRepository.findAllByCompanyId(company.getId())).thenReturn(expected);
        List<Contact> actual = contactService.findAllByCompanyId(company.getId());

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyFindByEmail() {
        String email = "john@example.com";
        Contact contact = new Contact(1L, "John", "Doe", email, "1234567890", "Description", new Company());

        when(contactRepository.findByEmail(email)).thenReturn(Optional.of(contact));
        Contact actual = contactService.findByEmail(email);

        assertEquals(contact, actual);
    }

    @Test
    void shouldThrowNotFindExceptionForFindByEmail() {
        String email = "john@example.com";

        when(contactRepository.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(EmptyResultDataAccessException.class, () -> contactService.findByEmail(email));
    }
}

