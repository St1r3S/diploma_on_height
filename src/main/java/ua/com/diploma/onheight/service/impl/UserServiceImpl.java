package ua.com.diploma.onheight.service.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.diploma.onheight.model.user.Department;
import ua.com.diploma.onheight.model.user.Post;
import ua.com.diploma.onheight.model.user.User;
import ua.com.diploma.onheight.model.user.UserRole;
import ua.com.diploma.onheight.repository.UserRepository;
import ua.com.diploma.onheight.service.UserService;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User save(User entity) {
        try {
            return userRepository.save(entity);
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to save entity " + entity, 1);
        }
    }

    @Override
    @Transactional
    public List<User> saveAll(List<User> entities) {
        try {
            return userRepository.saveAll(entities);
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to save entities " + entities, 1);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EmptyResultDataAccessException("There's no such user with id " + id, 1));
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllById(List<Long> ids) {
        return userRepository.findAllById(ids);
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return userRepository.count();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to delete entity with id " + id, 1);
        }
    }

    @Override
    @Transactional
    public void delete(User entity) {
        try {
            userRepository.deleteById(entity.getId());
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to delete entity " + entity, 1);
        }
    }

    @Override
    @Transactional
    public void deleteAllById(List<Long> ids) {
        try {
            userRepository.deleteAllById(ids);
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to delete entities with ids " + ids, 1);
        }
    }

    @Override
    @Transactional
    public void deleteAll(List<User> entities) {
        try {
            userRepository.deleteAll(entities);
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to delete entities " + entities, 1);
        }
    }

    @Override
    @Transactional
    public void deleteAll() {
        try {
            userRepository.deleteAll();
        } catch (Exception ex) {
            throw new EmptyResultDataAccessException("Unable to delete all entities ", 1);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new EmptyResultDataAccessException("There's no such user with login " + username, 1));
    }

    @Override
    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new EmptyResultDataAccessException("There's no such user with email " + email, 1));
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllByUserRole(UserRole userRole) {
        return userRepository.findAllByUserRole(userRole);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllByBirthday(LocalDate birthday) {
        return userRepository.findAllByBirthday(birthday);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllByDepartment(Department department) {
        return userRepository.findAllByDepartment(department);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllByPost(Post post) {
        return userRepository.findAllByPost(post);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllByDepartmentAndPost(Department department, Post post) {
        return userRepository.findAllByDepartmentAndPost(department, post);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllByCompanyId(Long companyId) {
        return userRepository.findAllByCompanyId(companyId);
    }
}
