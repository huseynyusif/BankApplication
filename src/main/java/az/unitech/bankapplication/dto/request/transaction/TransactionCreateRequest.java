package az.unitech.bankapplication.dto.request.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionCreateRequest {
    private String type;
    private double amount;
    private Long senderAccountId;
    private Long receiverAccountId;
}
