package az.unitech.bankapplication.dto.response.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionCreateResponse {
    private Long id;
    private String type;
    private double amount;
    private LocalDateTime date;
    private Long senderAccountId;
    private Long receiverAccountId;
}
