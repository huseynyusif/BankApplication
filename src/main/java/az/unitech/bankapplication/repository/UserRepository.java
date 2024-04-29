package az.unitech.bankapplication.repository;

import az.unitech.bankapplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByPin(String pin);
    boolean existsByEmail(String email);
}
