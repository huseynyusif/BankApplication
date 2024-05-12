package az.unitech.bankapplication.dto.response.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountCreateResponse {
    private Long id;
    private String accountNumber;
    private double balance;
    private String accountType;
    private LocalDate expirationDate;
    private String cardStatus;
    private String currency;
    private Long userId;
}
