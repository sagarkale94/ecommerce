package co.in.sagarkale.ecommerce.repositories;

import java.util.Optional;

import co.in.sagarkale.ecommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);

}
