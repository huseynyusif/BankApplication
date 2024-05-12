package az.unitech.bankapplication.dto.response.currency;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyRateReadResponse {
    private String currencyPair;
    private double rate;
    private LocalDateTime lastUpdated;
}
