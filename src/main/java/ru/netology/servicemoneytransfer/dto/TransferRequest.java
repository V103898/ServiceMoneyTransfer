package ru.netology.servicemoneytransfer.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Map;

@Data
public class TransferRequest {
    @NotBlank
    private String cardFromNumber;

    @NotBlank
    private String cardFromValidTill;

    @NotBlank
    @Size(min = 3, max = 3)
    private String cardFromCVV;

    @NotBlank
    private String cardToNumber;

    @Valid
    private Amount amount;

    }
