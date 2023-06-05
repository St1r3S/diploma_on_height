package ua.com.diploma.onheight.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import ua.com.diploma.onheight.model.company.Company;
import ua.com.diploma.onheight.repository.CompanyRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {CompanyServiceImpl.class})
class CompanyServiceImplTest {
    @Autowired
    CompanyServiceImpl companyService;
    @MockBean
    CompanyRepository companyRepository;

    @Test
    void shouldThrowNotFindException() {
        assertThrows(EmptyResultDataAccessException.class, () -> companyService.findById(6L));
    }

    @Test
    void shouldVerifyFindByCompanyName() {
        String companyName = "ABC Company";
        Company expected = new Company(1L, companyName, "abc@company.com", "1234567890");

        when(companyRepository.findByCompanyName(companyName)).thenReturn(Optional.of(expected));
        Company actual = companyService.findByCompanyName(companyName);

        assertEquals(expected, actual);
    }
}

