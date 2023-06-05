package ua.com.diploma.onheight.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import ua.com.diploma.onheight.model.request.CompletionStage;
import ua.com.diploma.onheight.model.request.Request;
import ua.com.diploma.onheight.model.request.RequestStatus;
import ua.com.diploma.onheight.repository.CompletionStageRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {CompletionStageServiceImpl.class})
class CompletionStageServiceImplTest {
    @Autowired
    CompletionStageServiceImpl completionStageService;
    @MockBean
    CompletionStageRepository completionStageRepository;

    @Test
    void shouldThrowNotFindException() {
        assertThrows(EmptyResultDataAccessException.class, () -> completionStageService.findById(6L));
    }

    @Test
    void shouldVerifyFindByRequestId() {
        Long requestId = 1L;
        CompletionStage expected = new CompletionStage(1L, RequestStatus.CREATED, LocalDateTime.now(), new Request());

        when(completionStageRepository.findByRequestId(requestId)).thenReturn(Optional.of(expected));
        CompletionStage actual = completionStageService.findByRequestId(requestId);

        assertEquals(expected, actual);
    }
}

