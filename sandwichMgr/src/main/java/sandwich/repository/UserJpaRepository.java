package sandwich.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sandwich.model.entities.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Integer> {
    User findUserByEmailAndPassword(String email, String password);
    User findUserByEmail(String email);
    User findByUsername(String username);
}