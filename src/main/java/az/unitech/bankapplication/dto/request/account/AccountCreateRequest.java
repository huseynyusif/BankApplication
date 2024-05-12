package az.unitech.bankapplication.dto.request.account;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountCreateRequest {
    @Pattern(regexp = "\\d{12}", message = "Account number must be 12 digits")
    private String accountNumber;
    private double balance;
    private String accountType;
    private String currency;
    private Long userId;
}
