package az.unitech.bankapplication.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "currency_rates",schema = "bank_application")
public class CurrencyRateEntity {

    @Id
    private String currencyPair; // Example: AZN,USD
    private double rate;
    private LocalDateTime lastUpdated;

}
