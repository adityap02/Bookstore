package org.ood.adporwal.bookstore.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ResponseDTO {
    private String id;
    private String status;
    private String message;
    private BigDecimal amount;
    private String transactionId;

    public ResponseDTO() {
    }
    public ResponseDTO(String id, String status, String message, BigDecimal amount, String transactionId) {
        this.id = id;
        this.status = status;
        this.message = message;
        this.amount = amount;
        this.transactionId = transactionId;
    }
}
