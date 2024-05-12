package az.unitech.bankapplication.dto.response.currency;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyRateCreateResponse {
    private Long id;
    private String currencyPair;
    private double rate;
}
