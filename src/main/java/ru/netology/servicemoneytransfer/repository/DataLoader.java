package ru.netology.servicemoneytransfer.repository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.netology.servicemoneytransfer.dto.Card;
import ru.netology.servicemoneytransfer.repository.CardRepository;

import java.math.BigDecimal;

@Component
public class DataLoader implements CommandLineRunner {
    private final CardRepository cardRepository;

    public DataLoader(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Card card1 = new Card();
        card1.setNumber("1111-2222-3333-4444");
        card1.setValidTill("12/25");
        card1.setCvv("123");
        card1.setCurrency("USD");
        card1.setBalance(BigDecimal.valueOf(1000));

        Card card2 = new Card();
        card2.setNumber("5555-6666-7777-8888");
        card2.setValidTill("11/24");
        card2.setCvv("456");
        card2.setCurrency("USD");
        card2.setBalance(BigDecimal.valueOf(500));

        cardRepository.save(card1);
        cardRepository.save(card2);
    }
}