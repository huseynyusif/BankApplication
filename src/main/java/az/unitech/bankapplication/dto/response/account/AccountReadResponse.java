package az.unitech.bankapplication.dto.response.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AccountReadResponse {
    private Long id;
    private String accountNumber;
    private double balance;
    private String accountType;
    private Long userId;
}
