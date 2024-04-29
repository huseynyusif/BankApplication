package az.unitech.bankapplication.dto.response.transaction;

import java.time.LocalDateTime;

public class TransactionReadResponse {
    private Long id;
    private String type;
    private double amount;
    private LocalDateTime date;
    private Long accountId;
}
