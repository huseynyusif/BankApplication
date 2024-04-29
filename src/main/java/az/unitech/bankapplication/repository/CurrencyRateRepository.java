package az.unitech.bankapplication.repository;

import az.unitech.bankapplication.entity.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRateRepository extends JpaRepository<CurrencyRate,Long> {
}
