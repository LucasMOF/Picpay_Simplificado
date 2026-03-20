package pratica.picpaySimplificado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pratica.picpaySimplificado.domain.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
