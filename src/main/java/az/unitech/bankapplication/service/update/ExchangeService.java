package az.unitech.bankapplication.service.update;

import az.unitech.bankapplication.entity.CurrencyRateEntity;
import az.unitech.bankapplication.repository.CurrencyRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class ExchangeService {

    private final RestTemplate restTemplate;
    private final CurrencyRateRepository currencyRateRepository;

    @Value("${fxratesapi.access.link}")
    private String link;

    @Value("${fxratesapi.access.token}")
    private String accessToken;

    //this method take information from fx-rates-api
    public double convertCurrencyPast(String sourceCurrency, String targetCurrency, double amount) {
        String url = "https://api.fxratesapi.com/latest?api_key=" + accessToken;
        ResponseEntity<Map<String, Object>> responseEntity = restTemplate.exchange(
                url, HttpMethod.GET, null, new ParameterizedTypeReference<Map<String, Object>>() {});

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            Map<String, Object> responseBody = responseEntity.getBody();
            if (responseBody != null && responseBody.containsKey("rates")) {
                Map<String, Object> rates = (Map<String, Object>) responseBody.get("rates");

                double sourceRate = 1.0;
                double targetRate = 1.0;


                Object sourceRateObj = rates.get(sourceCurrency);
                Object targetRateObj = rates.get(targetCurrency);

                if (sourceRateObj instanceof Double) {
                    sourceRate = (Double) sourceRateObj;
                }
                if (targetRateObj instanceof Double) {
                    targetRate = (Double) targetRateObj;
                }

                if (sourceCurrency.equals(targetCurrency)) {
                    return amount;
                } else {
                    if (sourceRate < targetRate) {
                        return amount * targetRate;
                    } else {
                        return amount / sourceRate;
                    }
                }
            }
        }
        throw new RuntimeException("Currency conversion failed");
    }


    //but this new method take information from our CurrencyRate entity class
    public double convertCurrency(String sourceCurrency, String targetCurrency, double amount) {
        CurrencyRateEntity sourceRateEntity = currencyRateRepository.findByCurrencyPair(sourceCurrency);
        CurrencyRateEntity targetRateEntity = currencyRateRepository.findByCurrencyPair(targetCurrency);

        if (sourceRateEntity != null && targetRateEntity != null) {
            double sourceRate = sourceRateEntity.getRate();
            double targetRate = targetRateEntity.getRate();

            if (sourceCurrency.equals(targetCurrency)) {
                return amount;
            } else {
                if (sourceRate < targetRate) {
                    return amount * targetRate;
                } else {
                    return amount / sourceRate;
                }
            }
        }

        throw new RuntimeException("Currency conversion failed");
    }


}
