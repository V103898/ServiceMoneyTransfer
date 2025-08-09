package ru.netology.servicemoneytransfer.model;
import lombok.Data;
import lombok.Builder;
import java.math.BigDecimal;

@Data
@Builder
public class Operation {
    private String id;
    private String cardFrom;
    private String cardTo;
    private BigDecimal amount;
    private String currency;
    private BigDecimal commission;
    private OperationStatus status;
    private String verificationCode;
}