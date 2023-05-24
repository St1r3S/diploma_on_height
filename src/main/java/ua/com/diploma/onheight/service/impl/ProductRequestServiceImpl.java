package ua.com.diploma.onheight.service.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.diploma.onheight.model.manytomany.ProductRequest;
import ua.com.diploma.onheight.model.manytomany.utility.ProductRequestKey;
import ua.com.diploma.onheight.repository.ProductRequestRepository;
import ua.com.diploma.onheight.service.ProductRequestService;

import java.util.List;

@Service
public class ProductRequestServiceImpl implements ProductRequestService {

    private final ProductRequestRepository productRequestRepository;

    public ProductRequestServiceImpl(ProductRequestRepository productRequestRepository) {
        this.productRequestRepository = productRequestRepository;
    }

    @Override
    @Transactional
    public ProductRequest save(ProductRequest entity) {
        try {
            return productRequestRepository.save(entity);
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to save entity " + entity, 1);
        }
    }

    @Override
    @Transactional
    public List<ProductRequest> saveAll(List<ProductRequest> entities) {
        try {
            return productRequestRepository.saveAll(entities);
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to save entities " + entities, 1);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ProductRequest findById(ProductRequestKey id) {
        return productRequestRepository.findById(id).orElseThrow(
                () -> new EmptyResultDataAccessException("There's no such product_request entity with id " + id, 1));
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(ProductRequestKey id) {
        return productRequestRepository.existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductRequest> findAll() {
        return productRequestRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductRequest> findAllById(List<ProductRequestKey> ids) {
        return productRequestRepository.findAllById(ids);
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return productRequestRepository.count();
    }

    @Override
    @Transactional
    public void deleteById(ProductRequestKey id) {
        try {
            productRequestRepository.deleteById(id);
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to delete entity with id " + id, 1);
        }
    }

    @Override
    @Transactional
    public void delete(ProductRequest entity) {
        try {
            productRequestRepository.deleteById(entity.getId());
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to delete entity " + entity, 1);
        }
    }

    @Override
    @Transactional
    public void deleteAllById(List<ProductRequestKey> ids) {
        try {
            productRequestRepository.deleteAllById(ids);
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to delete entities with ids " + ids, 1);
        }
    }

    @Override
    @Transactional
    public void deleteAll(List<ProductRequest> entities) {
        try {
            productRequestRepository.deleteAll(entities);
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to delete entities " + entities, 1);
        }
    }

    @Override
    @Transactional
    public void deleteAll() {
        try {
            productRequestRepository.deleteAll();
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to delete all entities ", 1);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Integer findProductQtySumByProductId(Long productId) {
        return productRequestRepository.findProductQtySumByProductId(productId);
    }
}
