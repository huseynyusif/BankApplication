package az.unitech.bankapplication.repository;

import az.unitech.bankapplication.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity,Long> {

    List<AccountEntity> findByUser_UserName(String username);

}
