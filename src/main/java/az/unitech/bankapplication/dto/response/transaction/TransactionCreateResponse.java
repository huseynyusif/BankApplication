package az.unitech.bankapplication.dto.response.transaction;

import java.time.LocalDateTime;

public class TransactionCreateResponse {
    private Long id;
    private String type;
    private double amount;
    private LocalDateTime date;
    private Long accountId;
}
