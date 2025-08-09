package ru.netology.servicemoneytransfer.service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.netology.servicemoneytransfer.dto.*;
import ru.netology.servicemoneytransfer.exception.*;
import ru.netology.servicemoneytransfer.log.TransferLogService;
import ru.netology.servicemoneytransfer.model.Operation;
import ru.netology.servicemoneytransfer.model.OperationStatus;
import ru.netology.servicemoneytransfer.repository.CardRepository;
import ru.netology.servicemoneytransfer.repository.OperationRepository;
import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransferService {
    private final CardRepository cardRepository;
    private final OperationRepository operationRepository;
    private final TransferLogService logService;

    @Transactional
    public String transfer(TransferRequest request) {
        // Валидация карт
        Card cardFrom = validateCard(request.getCardFromNumber(),
                request.getCardFromValidTill(),
                request.getCardFromCVV());
        Card cardTo = cardRepository.findByNumber(request.getCardToNumber())
                .orElseThrow(() -> new CardNotFoundException("Card to not found"));

        // Проверка валюты и баланса
        if (!cardFrom.getCurrency().equals(cardTo.getCurrency())) {
            throw new CurrencyMismatchException("Currency mismatch");
        }

        BigDecimal amount = BigDecimal.valueOf(request.getAmount().getValue());
        if (cardFrom.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient funds");
        }

        // Создание операции
        String operationId = UUID.randomUUID().toString();
        Operation operation = Operation.builder()
                .id(operationId)
                .cardFrom(cardFrom.getNumber())
                .cardTo(cardTo.getNumber())
                .amount(amount)
                .currency(request.getAmount().getCurrency())
                .status(OperationStatus.PENDING)
                .verificationCode(generateVerificationCode())
                .build();

        operationRepository.save(operation);

        // Логирование
        logService.logTransfer(operation, "Transfer initiated");

        return operationId;
    }

    @Transactional
    public String confirmOperation(ConfirmOperationRequest request) {
        Operation operation = operationRepository.findById(request.getOperationId())
                .orElseThrow(() -> new OperationNotFoundException("Operation not found"));

        if (!operation.getVerificationCode().equals(request.getCode())) {
            throw new InvalidVerificationCodeException("Invalid verification code");
        }

        // Выполнение перевода
        Card cardFrom = cardRepository.findByNumber(operation.getCardFrom())
                .orElseThrow(() -> new CardNotFoundException("Card from not found"));
        Card cardTo = cardRepository.findByNumber(operation.getCardTo())
                .orElseThrow(() -> new CardNotFoundException("Card to not found"));

        BigDecimal commission = calculateCommission(operation.getAmount());
        BigDecimal totalAmount = operation.getAmount().add(commission);

        cardFrom.setBalance(cardFrom.getBalance().subtract(totalAmount));
        cardTo.setBalance(cardTo.getBalance().add(operation.getAmount()));

        operation.setStatus(OperationStatus.COMPLETED);
        operation.setCommission(commission);
        operationRepository.save(operation);

        // Логирование
        logService.logTransfer(operation, "Transfer completed");

        return operation.getId();
    }

    private Card validateCard(String cardNumber, String validTill, String cvv) {
        Card card = cardRepository.findByNumber(cardNumber)
                .orElseThrow(() -> new CardNotFoundException("Card from not found"));

        if (!card.getValidTill().equals(validTill) || !card.getCvv().equals(cvv)) {
            throw new InvalidCardDetailsException("Invalid card details");
        }
        return card;
    }

    private String generateVerificationCode() {
        return String.format("%04d", new Random().nextInt(10000));
    }

    private BigDecimal calculateCommission(BigDecimal amount) {
        return amount.multiply(BigDecimal.valueOf(0.01)); // 1% комиссия
    }
}