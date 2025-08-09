package ru.netology.servicemoneytransfer.dto;
import lombok.Data;

@Data
public class ConfirmOperationRequest {
    private String operationId;

    private String code;
}