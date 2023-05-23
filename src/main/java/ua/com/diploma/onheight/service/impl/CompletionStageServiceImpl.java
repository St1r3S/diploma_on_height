package ua.com.diploma.onheight.service.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.diploma.onheight.model.request.CompletionStage;
import ua.com.diploma.onheight.repository.CompletionStageRepository;
import ua.com.diploma.onheight.service.CompletionStageService;

import java.util.List;

@Service
public class CompletionStageServiceImpl implements CompletionStageService {

    private final CompletionStageRepository completionStageRepository;

    public CompletionStageServiceImpl(CompletionStageRepository completionStageRepository) {
        this.completionStageRepository = completionStageRepository;
    }

    @Override
    @Transactional
    public CompletionStage save(CompletionStage entity) {
        try {
            return completionStageRepository.save(entity);
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to save entity " + entity, 1);
        }
    }

    @Override
    @Transactional
    public List<CompletionStage> saveAll(List<CompletionStage> entities) {
        try {
            return completionStageRepository.saveAll(entities);
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to save entities " + entities, 1);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public CompletionStage findById(Long id) {
        return completionStageRepository.findById(id).orElseThrow(
                () -> new EmptyResultDataAccessException("There's no such academic year with id " + id, 1));
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return completionStageRepository.existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompletionStage> findAll() {
        return completionStageRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompletionStage> findAllById(List<Long> ids) {
        return completionStageRepository.findAllById(ids);
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return completionStageRepository.count();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        try {
            completionStageRepository.deleteById(id);
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to delete entity with id " + id, 1);
        }
    }

    @Override
    @Transactional
    public void delete(CompletionStage entity) {
        try {
            completionStageRepository.deleteById(entity.getId());
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to delete entity " + entity, 1);
        }
    }

    @Override
    @Transactional
    public void deleteAllById(List<Long> ids) {
        try {
            completionStageRepository.deleteAllById(ids);
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to delete entities with ids " + ids, 1);
        }
    }

    @Override
    @Transactional
    public void deleteAll(List<CompletionStage> entities) {
        try {
            completionStageRepository.deleteAll(entities);
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to delete entities " + entities, 1);
        }
    }

    @Override
    @Transactional
    public void deleteAll() {
        try {
            completionStageRepository.deleteAll();
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to delete all entities ", 1);
        }
    }
}
