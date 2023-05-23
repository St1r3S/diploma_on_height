package ua.com.diploma.onheight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.diploma.onheight.model.user.Department;
import ua.com.diploma.onheight.model.user.Post;
import ua.com.diploma.onheight.model.user.User;
import ua.com.diploma.onheight.model.user.UserRole;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    List<User> findAllByUserRole(UserRole userRole);

    List<User> findAllByBirthday(LocalDate birthday);

    List<User> findAllByDepartment(Department department);

    List<User> findAllByPost(Post post);

    List<User> findAllByDepartmentAndPost(Department department, Post post);

    List<User> findAllByCompanyId(Long companyId);
}
