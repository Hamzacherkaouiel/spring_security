package security.spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import security.spring.Models.Users;

import java.util.Optional;

public interface Userepo extends JpaRepository<Users,Integer> {
    Optional<Users> findByUsername(String username);
}
