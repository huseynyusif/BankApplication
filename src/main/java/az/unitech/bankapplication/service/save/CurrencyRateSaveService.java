package az.unitech.bankapplication.service.save;

import az.unitech.bankapplication.entity.CurrencyRateEntity;
import az.unitech.bankapplication.repository.CurrencyRateRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class CurrencyRateSaveService {

    private final CurrencyRateRepository currencyRateRepository;

    @Transactional
    public void saveCurrencyRate(String currencyPair, double rate) {
        CurrencyRateEntity currencyRate = new CurrencyRateEntity();
        currencyRate.setCurrencyPair(currencyPair);
        currencyRate.setRate(rate);
        currencyRate.setLastUpdated(LocalDateTime.now());
        currencyRateRepository.save(currencyRate);
    }
}
