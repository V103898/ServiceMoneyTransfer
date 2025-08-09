package ru.netology.servicemoneytransfer.repository;
import org.springframework.stereotype.Repository;
import ru.netology.servicemoneytransfer.dto.Card;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.Optional;

@Repository
public class CardRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Card> findByNumber(String cardNumber) {
        TypedQuery<Card> query = entityManager.createQuery(
                "SELECT c FROM Card c WHERE c.number = :number", Card.class);
        query.setParameter("number", cardNumber);
        try {
            return Optional.of(query.getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public void save(Card card) {
        if (card.getId() == null) {
            entityManager.persist(card);
        } else {
            entityManager.merge(card);
        }
    }
}