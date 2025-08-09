package ru.netology.servicemoneytransfer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.servicemoneytransfer.dto.TransferRequest;
import ru.netology.servicemoneytransfer.exception.CardNotFoundException;
import ru.netology.servicemoneytransfer.repository.CardRepository;
import ru.netology.servicemoneytransfer.repository.OperationRepository;
import ru.netology.servicemoneytransfer.service.TransferService;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransferServiceTest {
    @Mock
    private CardRepository cardRepository;
    @Mock
    private OperationRepository operationRepository;

    @InjectMocks
    private TransferService transferService;

    @Test
    void transfer_shouldFailWhenCardFromNotFound() {
        TransferRequest request = new TransferRequest();


        when(cardRepository.findByNumber(any())).thenReturn(Optional.empty());

        assertThrows(CardNotFoundException.class, () -> transferService.transfer(request));
    }


}