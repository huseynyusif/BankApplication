package az.unitech.bankapplication.repository;

import az.unitech.bankapplication.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity,Long> {
}
