package ua.com.diploma.onheight.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import ua.com.diploma.onheight.model.company.Company;
import ua.com.diploma.onheight.model.user.*;
import ua.com.diploma.onheight.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {UserServiceImpl.class})
class UserServiceImplTest {

    @Autowired
    UserServiceImpl userService;

    @MockBean
    UserRepository userRepository;

    @Test
    void shouldThrowNotFindException() {
        assertThrows(EmptyResultDataAccessException.class, () -> userService.findById(6L));
    }

    @Test
    void shouldFindByUsername() {
        String username = "john.doe";
        User user = new User(1L, UserRole.USER, "John", "Doe", "john.doe@example.com", "123456789", username, "password", LocalDate.now(), Gender.MALE, Department.HR, Post.MANAGER, new Company());

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        User result = userService.findByUsername(username);

        assertEquals(user, result);
    }

    @Test
    void shouldThrowExceptionWhenUsernameNotFound() {
        String username = "john.doe";

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        assertThrows(EmptyResultDataAccessException.class, () -> userService.findByUsername(username));
    }

    @Test
    void shouldFindByEmail() {
        String email = "john.doe@example.com";
        User user = new User(1L, UserRole.USER, "John", "Doe", email, "123456789", "john.doe", "password", LocalDate.now(), Gender.MALE, Department.HR, Post.MANAGER, new Company());

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        User result = userService.findByEmail(email);

        assertEquals(user, result);
    }

    @Test
    void shouldThrowExceptionWhenEmailNotFound() {
        String email = "john.doe@example.com";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(EmptyResultDataAccessException.class, () -> userService.findByEmail(email));
    }

    @Test
    void shouldFindAllByUserRole() {
        UserRole userRole = UserRole.USER;
        User user1 = new User(1L, userRole, "John", "Doe", "john.doe@example.com", "123456789", "john.doe", "password", LocalDate.now(), Gender.MALE, Department.HR, Post.MANAGER, new Company());
        User user2 = new User(2L, userRole, "Jane", "Smith", "jane.smith@example.com", "987654321", "jane.smith", "password", LocalDate.now(), Gender.FEMALE, Department.HR, Post.MANAGER, new Company());
        List<User> expectedUsers = List.of(user1, user2);

        when(userRepository.findAllByUserRole(userRole)).thenReturn(expectedUsers);
        List<User> result = userService.findAllByUserRole(userRole);

        assertEquals(expectedUsers, result);
    }

    @Test
    void shouldFindAllByBirthday() {
        LocalDate birthday = LocalDate.of(1990, 5, 15);
        User user1 = new User(1L, UserRole.USER, "John", "Doe", "john.doe@example.com", "123456789", "john.doe", "password", birthday, Gender.MALE, Department.HR, Post.MANAGER, new Company());
        User user2 = new User(2L, UserRole.USER, "Jane", "Smith", "jane.smith@example.com", "987654321", "jane.smith", "password", birthday, Gender.FEMALE, Department.HR, Post.MANAGER, new Company());
        List<User> expectedUsers = List.of(user1, user2);

        when(userRepository.findAllByBirthday(birthday)).thenReturn(expectedUsers);
        List<User> result = userService.findAllByBirthday(birthday);

        assertEquals(expectedUsers, result);
    }

    @Test
    void shouldFindAllByDepartment() {
        Department department = Department.HR;
        User user1 = new User(1L, UserRole.USER, "John", "Doe", "john.doe@example.com", "123456789", "john.doe", "password", LocalDate.now(), Gender.MALE, department, Post.MANAGER, new Company());
        User user2 = new User(2L, UserRole.USER, "Jane", "Smith", "jane.smith@example.com", "987654321", "jane.smith", "password", LocalDate.now(), Gender.FEMALE, department, Post.MANAGER, new Company());
        List<User> expectedUsers = List.of(user1, user2);

        when(userRepository.findAllByDepartment(department)).thenReturn(expectedUsers);
        List<User> result = userService.findAllByDepartment(department);

        assertEquals(expectedUsers, result);
    }

    @Test
    void shouldFindAllByPost() {
        Post post = Post.MANAGER;
        User user1 = new User(1L, UserRole.USER, "John", "Doe", "john.doe@example.com", "123456789", "john.doe", "password", LocalDate.now(), Gender.MALE, Department.HR, post, new Company());
        User user2 = new User(2L, UserRole.USER, "Jane", "Smith", "jane.smith@example.com", "987654321", "jane.smith", "password", LocalDate.now(), Gender.FEMALE, Department.HR, post, new Company());
        List<User> expectedUsers = List.of(user1, user2);

        when(userRepository.findAllByPost(post)).thenReturn(expectedUsers);
        List<User> result = userService.findAllByPost(post);

        assertEquals(expectedUsers, result);
    }

    @Test
    void shouldFindAllByDepartmentAndPost() {
        Department department = Department.HR;
        Post post = Post.MANAGER;
        User user1 = new User(1L, UserRole.USER, "John", "Doe", "john.doe@example.com", "123456789", "john.doe", "password", LocalDate.now(), Gender.MALE, department, post, new Company());
        User user2 = new User(2L, UserRole.USER, "Jane", "Smith", "jane.smith@example.com", "987654321", "jane.smith", "password", LocalDate.now(), Gender.FEMALE, department, post, new Company());
        List<User> expectedUsers = List.of(user1, user2);

        when(userRepository.findAllByDepartmentAndPost(department, post)).thenReturn(expectedUsers);
        List<User> result = userService.findAllByDepartmentAndPost(department, post);

        assertEquals(expectedUsers, result);
    }

    @Test
    void shouldFindAllByCompanyId() {
        Long companyId = 1L;
        User user1 = new User(1L, UserRole.USER, "John", "Doe", "john.doe@example.com", "123456789", "john.doe", "password", LocalDate.now(), Gender.MALE, Department.HR, Post.MANAGER, new Company());
        User user2 = new User(2L, UserRole.USER, "Jane", "Smith", "jane.smith@example.com", "987654321", "jane.smith", "password", LocalDate.now(), Gender.FEMALE, Department.HR, Post.MANAGER, new Company());
        List<User> expectedUsers = List.of(user1, user2);

        when(userRepository.findAllByCompanyId(companyId)).thenReturn(expectedUsers);
        List<User> result = userService.findAllByCompanyId(companyId);

        assertEquals(expectedUsers, result);
    }
}

