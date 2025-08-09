package ru.netology.servicemoneytransfer.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.netology.servicemoneytransfer.model.Operation;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class TransferLogService {
    private static final String LOG_FILE = "transfer.log";

    public void logTransfer(Operation operation, String message) {
        String logEntry = String.format("%s | %s | From: %s | To: %s | Amount: %s %s | Commission: %s | Status: %s | Message: %s%n",
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                operation.getId(),
                maskCardNumber(operation.getCardFrom()),
                maskCardNumber(operation.getCardTo()),
                operation.getAmount(),
                operation.getCurrency(),
                operation.getCommission() != null ? operation.getCommission() : "N/A",
                operation.getStatus(),
                message);

        try {
            Files.write(Paths.get(LOG_FILE),
                    logEntry.getBytes(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            log.error("Failed to write to log file", e);
        }
    }

    private String maskCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.length() < 8) {
            return cardNumber;
        }
        return cardNumber.substring(0, 4) + "****" + cardNumber.substring(cardNumber.length() - 4);
    }
}