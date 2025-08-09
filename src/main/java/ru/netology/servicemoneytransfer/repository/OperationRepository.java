package ru.netology.servicemoneytransfer.repository;

import org.springframework.stereotype.Repository;
import ru.netology.servicemoneytransfer.model.Operation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class OperationRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Operation operation) {
        if (operation.getId() == null) {
            entityManager.persist(operation);
        } else {
            entityManager.merge(operation);
        }
    }

    public Optional<Operation> findById(String operationId) {
        Operation operation = entityManager.find(Operation.class, operationId);
        return Optional.ofNullable(operation);
    }
}