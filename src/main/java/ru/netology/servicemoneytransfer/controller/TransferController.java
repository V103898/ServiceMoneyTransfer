package ru.netology.servicemoneytransfer.controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.servicemoneytransfer.dto.ConfirmOperationRequest;
import ru.netology.servicemoneytransfer.dto.OperationResponse;
import ru.netology.servicemoneytransfer.dto.TransferRequest;
import ru.netology.servicemoneytransfer.service.TransferService;

@RestController
@RequestMapping("/transfer")
@RequiredArgsConstructor
public class TransferController {
    private final TransferService transferService;

    @PostMapping
    public ResponseEntity<OperationResponse> transfer(@Valid @RequestBody TransferRequest request) {
        String operationId = transferService.transfer(request);
        return ResponseEntity.ok(new OperationResponse(operationId));
    }

    @PostMapping("/confirmOperation")
    public ResponseEntity<OperationResponse> confirmOperation(
            @Valid @RequestBody ConfirmOperationRequest request) {
        String operationId = transferService.confirmOperation(request);
        return ResponseEntity.ok(new OperationResponse(operationId));
    }
}