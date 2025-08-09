package ru.netology.servicemoneytransfer.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Amount {
    @Min(1)
    private int value;

    @NotBlank
    private String currency;
}