package ua.com.diploma.onheight.service.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.diploma.onheight.model.request.DeliveryMethod;
import ua.com.diploma.onheight.model.request.PaymentMethod;
import ua.com.diploma.onheight.model.request.Request;
import ua.com.diploma.onheight.model.request.RequestStatus;
import ua.com.diploma.onheight.repository.RequestRepository;
import ua.com.diploma.onheight.service.RequestService;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;

    public RequestServiceImpl(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    @Transactional
    public Request save(Request entity) {
        try {
            return requestRepository.save(entity);
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to save entity " + entity, 1);
        }
    }

    @Override
    @Transactional
    public List<Request> saveAll(List<Request> entities) {
        try {
            return requestRepository.saveAll(entities);
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to save entities " + entities, 1);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Request findById(Long id) {
        return requestRepository.findById(id).orElseThrow(
                () -> new EmptyResultDataAccessException("There's no such request with id " + id, 1));
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return requestRepository.existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Request> findAll() {
        return requestRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Request> findAllById(List<Long> ids) {
        return requestRepository.findAllById(ids);
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return requestRepository.count();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        try {
            requestRepository.deleteById(id);
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to delete entity with id " + id, 1);
        }
    }

    @Override
    @Transactional
    public void delete(Request entity) {
        try {
            requestRepository.deleteById(entity.getId());
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to delete entity " + entity, 1);
        }
    }

    @Override
    @Transactional
    public void deleteAllById(List<Long> ids) {
        try {
            requestRepository.deleteAllById(ids);
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to delete entities with ids " + ids, 1);
        }
    }

    @Override
    @Transactional
    public void deleteAll(List<Request> entities) {
        try {
            requestRepository.deleteAll(entities);
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to delete entities " + entities, 1);
        }
    }

    @Override
    @Transactional
    public void deleteAll() {
        try {
            requestRepository.deleteAll();
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to delete all entities ", 1);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Request> findAllByCompanyId(Long companyId) {
        return requestRepository.findAllByCompanyId(companyId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Request> findAllByCompanyIdAndLastName(Long companyId, String lastName) {
        return requestRepository.findAllByCompanyIdAndLastName(companyId, lastName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Request> findAllByCompanyIdAndEmail(Long companyId, String email) {
        return requestRepository.findAllByCompanyIdAndEmail(companyId, email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Request> findAllByCompanyIdAndDeliveryMethod(Long companyId, DeliveryMethod deliveryMethod) {
        return requestRepository.findAllByCompanyIdAndDeliveryMethod(companyId, deliveryMethod);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Request> findAllByCompanyIdAndPaymentMethod(Long companyId, PaymentMethod paymentMethod) {
        return requestRepository.findAllByCompanyIdAndPaymentMethod(companyId, paymentMethod);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Request> findAllByCompanyIdAndCompletionStage_RequestStatus(Long companyId, RequestStatus requestStatus) {
        return requestRepository.findAllByCompanyIdAndCompletionStage_RequestStatus(companyId, requestStatus);
    }
}
