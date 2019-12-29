package sec.project.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sec.project.domain.UserRole;

import java.util.List;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {
    @Query("SELECT ur.role FROM UserRole ur, User u WHERE u.username=?1 AND ur.userId = u.id")
    List<String> findRoleByUsername(String username);
}
