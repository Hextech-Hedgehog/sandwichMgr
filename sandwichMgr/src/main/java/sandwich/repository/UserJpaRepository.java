package sandwich.repository;

import com.sun.xml.bind.v2.TODO;
import org.springframework.data.jpa.repository.JpaRepository;
import sandwich.exception.UserNotFoundException;
import sandwich.model.User;

import java.util.List;


public interface UserJpaRepository extends JpaRepository<User, Integer> {


    User findByFirstName(String firstName) throws UserNotFoundException;  // TODO check default methods in serivce





}
