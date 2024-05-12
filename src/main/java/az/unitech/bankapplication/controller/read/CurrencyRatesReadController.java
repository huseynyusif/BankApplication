package az.unitech.bankapplication.controller.read;

import az.unitech.bankapplication.dto.response.currency.ExchangeRatesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@RestController
@RequestMapping("/currency-rates")
public class CurrencyRatesReadController {

    @Value("${fxratesapi.access.token}")
    private String accessToken;

    private final String FX_RATES_API_URL = "https://api.fxratesapi.com/latest?api_key=";

    private final RestTemplate restTemplate;



    @GetMapping("/latest")
    public ResponseEntity<ExchangeRatesResponse> getLatestExchangeRates() {
        String url = FX_RATES_API_URL + accessToken;
        ExchangeRatesResponse exchangeRatesResponse = restTemplate.getForObject(url, ExchangeRatesResponse.class);
        return ResponseEntity.ok(exchangeRatesResponse);
    }


}

