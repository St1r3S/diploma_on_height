package ua.com.diploma.onheight.service.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.diploma.onheight.model.product.Product;
import ua.com.diploma.onheight.model.product.ProductType;
import ua.com.diploma.onheight.repository.ProductRepository;
import ua.com.diploma.onheight.service.ProductService;

import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public Product save(Product entity) {
        try {
            return productRepository.save(entity);
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to save entity " + entity, 1);
        }
    }

    @Override
    @Transactional
    public List<Product> saveAll(List<Product> entities) {
        try {
            return productRepository.saveAll(entities);
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to save entities " + entities, 1);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new EmptyResultDataAccessException("There's no such product with id " + id, 1));
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return productRepository.existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAllById(List<Long> ids) {
        return productRepository.findAllById(ids);
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return productRepository.count();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to delete entity with id " + id, 1);
        }
    }

    @Override
    @Transactional
    public void delete(Product entity) {
        try {
            productRepository.deleteById(entity.getId());
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to delete entity " + entity, 1);
        }
    }

    @Override
    @Transactional
    public void deleteAllById(List<Long> ids) {
        try {
            productRepository.deleteAllById(ids);
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to delete entities with ids " + ids, 1);
        }
    }

    @Override
    @Transactional
    public void deleteAll(List<Product> entities) {
        try {
            productRepository.deleteAll(entities);
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to delete entities " + entities, 1);
        }
    }

    @Override
    @Transactional
    public void deleteAll() {
        try {
            productRepository.deleteAll();
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to delete all entities ", 1);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAllByProductType(ProductType productType) {
        return productRepository.findAllByProductType(productType);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAllByCompanyId(Long companyId) {
        return productRepository.findAllByCompanyId(companyId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAllByWarehouseId(Long warehouseId) {
        return productRepository.findAllByWarehouseId(warehouseId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAllByWarehouseIdAndProductType(Long warehouseId, ProductType productType) {
        return productRepository.findAllByWarehouseIdAndProductType(warehouseId, productType);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAllByWarehouseIdAndProductName(Long warehouseId, String productName) {
        return productRepository.findAllByWarehouseIdAndProductName(warehouseId, productName);
    }

    @Override
    @Transactional(readOnly = true)
    public Product findByProductTypeAndProductNameAndWarehouseIdAndCompanyId(ProductType productType, String productName, Long warehouseId, Long companyId) {
        return productRepository.findByProductTypeAndProductNameAndWarehouseIdAndCompanyId(productType, productName, warehouseId, companyId).orElseThrow(
                () -> new EmptyResultDataAccessException("There's no such product with parameters: " +
                        Map.of(
                                "productType", productType.getKey(),
                                "productName", productName,
                                "warehouseId", warehouseId.toString(),
                                "companyId", companyId.toString()
                        ), 1));
    }
}
