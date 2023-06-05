package ua.com.diploma.onheight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.diploma.onheight.model.company.Contact;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findAllByCompanyId(Long companyId);

    List<Contact> findAllByCompanyIdAndLastName(Long companyId, String lastName);

    List<Contact> findAllByCompanyIdAndEmail(Long companyId, String email);

    Optional<Contact> findByEmail(String email);

    Optional<Contact> findByPhoneNumber(String phoneNumber);
}
