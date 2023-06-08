package ua.com.diploma.onheight.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import ua.com.diploma.onheight.model.request.DeliveryMethod;
import ua.com.diploma.onheight.model.request.PaymentMethod;
import ua.com.diploma.onheight.model.request.Request;
import ua.com.diploma.onheight.model.request.RequestStatus;
import ua.com.diploma.onheight.repository.RequestRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {RequestServiceImpl.class})
class RequestServiceImplTest {
    @Autowired
    RequestServiceImpl requestService;
    @MockBean
    RequestRepository requestRepository;

    @Test
    void shouldThrowNotFindException() {
        assertThrows(EmptyResultDataAccessException.class, () -> requestService.findById(6L));
    }

    @Test
    void shouldVerifyFindAllByCompanyId() {
        Long companyId = 1L;
        Request request1 = new Request(1L, "John", "Doe", "john.doe@example.com", "123456789", null, DeliveryMethod.COURIER, "Address 1", PaymentMethod.CARD, null);
        Request request2 = new Request(2L, "Jane", "Smith", "jane.smith@example.com", "987654321", null, DeliveryMethod.COURIER, "Address 2", PaymentMethod.CASH, null);
        List<Request> expected = List.of(request2, request1);

        when(requestRepository.findAllByCompanyIdOrderByIdDesc(companyId)).thenReturn(expected);
        List<Request> actual = requestService.findAllByCompanyId(companyId);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyFindAllByCompanyIdAndLastName() {
        Long companyId = 1L;
        String lastName = "Doe";
        Request request1 = new Request(1L, "John", "Doe", "john.doe@example.com", "123456789", null, DeliveryMethod.COURIER, "Address 1", PaymentMethod.CARD, null);
        List<Request> expected = List.of(request1);

        when(requestRepository.findAllByCompanyIdAndLastName(companyId, lastName)).thenReturn(expected);
        List<Request> actual = requestService.findAllByCompanyIdAndLastName(companyId, lastName);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyFindAllByCompanyIdAndEmail() {
        Long companyId = 1L;
        String email = "john.doe@example.com";
        Request request1 = new Request(1L, "John", "Doe", "john.doe@example.com", "123456789", null, DeliveryMethod.COURIER, "Address 1", PaymentMethod.CARD, null);
        List<Request> expected = List.of(request1);

        when(requestRepository.findAllByCompanyIdAndEmail(companyId, email)).thenReturn(expected);
        List<Request> actual = requestService.findAllByCompanyIdAndEmail(companyId, email);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyFindAllByCompanyIdAndDeliveryMethod() {
        Long companyId = 1L;
        DeliveryMethod deliveryMethod = DeliveryMethod.COURIER;
        Request request1 = new Request(1L, "John", "Doe", "john.doe@example.com", "123456789", null, DeliveryMethod.COURIER, "Address 1", PaymentMethod.CARD, null);
        List<Request> expected = List.of(request1);

        when(requestRepository.findAllByCompanyIdAndDeliveryMethod(companyId, deliveryMethod)).thenReturn(expected);
        List<Request> actual = requestService.findAllByCompanyIdAndDeliveryMethod(companyId, deliveryMethod);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyFindAllByCompanyIdAndPaymentMethod() {
        Long companyId = 1L;
        PaymentMethod paymentMethod = PaymentMethod.CARD;
        Request request1 = new Request(1L, "John", "Doe", "john.doe@example.com", "123456789", null, DeliveryMethod.COURIER, "Address 1", PaymentMethod.CARD, null);
        List<Request> expected = List.of(request1);

        when(requestRepository.findAllByCompanyIdAndPaymentMethod(companyId, paymentMethod)).thenReturn(expected);
        List<Request> actual = requestService.findAllByCompanyIdAndPaymentMethod(companyId, paymentMethod);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyFindAllByCompanyIdAndCompletionStage_RequestStatus() {
        Long companyId = 1L;
        RequestStatus requestStatus = RequestStatus.CONFIRMED;
        Request request1 = new Request(1L, "John", "Doe", "john.doe@example.com", "123456789", null, DeliveryMethod.COURIER, "Address 1", PaymentMethod.CARD, null);
        List<Request> expected = List.of(request1);

        when(requestRepository.findAllByCompanyIdAndCompletionStage_RequestStatus(companyId, requestStatus)).thenReturn(expected);
        List<Request> actual = requestService.findAllByCompanyIdAndCompletionStage_RequestStatus(companyId, requestStatus);

        assertEquals(expected, actual);
    }
}

