package az.unitech.bankapplication.repository;

import az.unitech.bankapplication.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
}
