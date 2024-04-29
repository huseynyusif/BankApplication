package az.unitech.bankapplication.repository;

import az.unitech.bankapplication.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
