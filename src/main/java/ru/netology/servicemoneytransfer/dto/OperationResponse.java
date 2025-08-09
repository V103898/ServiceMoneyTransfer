package ru.netology.servicemoneytransfer.dto;

import lombok.Data;

@Data
public class OperationResponse {
    private String operationId;

    public OperationResponse(String operationId) {
        this.operationId = operationId;
    }
}