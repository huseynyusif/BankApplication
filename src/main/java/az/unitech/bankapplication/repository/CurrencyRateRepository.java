package az.unitech.bankapplication.repository;

import az.unitech.bankapplication.entity.CurrencyRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRateRepository extends JpaRepository<CurrencyRateEntity,Long> {
    CurrencyRateEntity findByCurrencyPair(String currencyPair);
}
