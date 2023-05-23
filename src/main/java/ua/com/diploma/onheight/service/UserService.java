package ua.com.diploma.onheight.service;

import ua.com.diploma.onheight.model.user.User;
import ua.com.diploma.onheight.model.user.UserRole;

import java.time.LocalDate;
import java.util.List;

public interface UserService extends CrudService<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);

    List<User> findAllByUserRole(UserRole userRole);

    List<User> findAllByBirthday(LocalDate birthday);
}
