package pratica.picpaySimplificado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pratica.picpaySimplificado.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

   Optional<User> findUserById(Long id);
}
