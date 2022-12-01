package sandwich.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sandwich.exception.UserNotFoundException;
import sandwich.model.User;


public interface UserJpaRepository extends JpaRepository<User, Integer> {


    User findByFirstName(String firstName) throws UserNotFoundException;





}
