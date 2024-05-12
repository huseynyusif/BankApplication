package az.unitech.bankapplication.repository;

import az.unitech.bankapplication.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    boolean existsByPin(String pin);
    boolean existsByEmail(String email);
    boolean existsByUserName(String userName);

    UserEntity findByUserName(String username);
}
