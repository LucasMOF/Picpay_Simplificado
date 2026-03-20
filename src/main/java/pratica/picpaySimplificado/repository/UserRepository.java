package pratica.picpaySimplificado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pratica.picpaySimplificado.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {


}
