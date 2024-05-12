package az.unitech.bankapplication.service.update;

import az.unitech.bankapplication.service.save.CurrencyRateSaveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyRateUpdateService {

    private final RestTemplate restTemplate;
    private final CurrencyRateSaveService currencyRateSaveService;
    @Value("${fxratesapi.access.link}")
    private String link;
    @Value("${fxratesapi.access.token}")
    private String accessToken;

//    @Scheduled(fixedRate = 300000)
    public void updateCurrencyData() {

        ResponseEntity<Map> response = restTemplate.getForEntity(link+accessToken, Map.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            Map<String, ?> responseBody = response.getBody();
            if (responseBody != null && responseBody.containsKey("rates")) {
                Map<String, ?> rates = (Map<String, ?>) responseBody.get("rates");

                for (Map.Entry<String, ?> entry : rates.entrySet()) {
                    String currencyPair = entry.getKey();
                    if (entry.getValue() instanceof Double) {
                        double rate = (Double) entry.getValue();
                        currencyRateSaveService.saveCurrencyRate(currencyPair, rate);
                    } else if (entry.getValue() instanceof Integer) {
                        int rate = (Integer) entry.getValue();
                        currencyRateSaveService.saveCurrencyRate(currencyPair, rate);
                    }
                }
                log.info("Currency data updated at " + LocalDateTime.now());
            }
        } else {
            log.error("Failed to update currency data. Status code: " + response.getStatusCodeValue());
        }
    }
}
