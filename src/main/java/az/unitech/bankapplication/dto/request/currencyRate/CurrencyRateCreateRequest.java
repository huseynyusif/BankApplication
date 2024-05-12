package az.unitech.bankapplication.dto.request.currencyRate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyRateCreateRequest {
    private String currencyPair;
    private double rate;
}
