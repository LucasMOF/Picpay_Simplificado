package pratica.picpaySimplificado.service;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pratica.picpaySimplificado.domain.Transaction;
import pratica.picpaySimplificado.domain.User;
import pratica.picpaySimplificado.dtos.RequestTransaction;
import pratica.picpaySimplificado.repository.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private RestTemplate restTemplate;

    public Transaction createTransaction(RequestTransaction transaction) throws Exception {
        User sender = this.userService.findById(transaction.senderId());
        User receiver = this.userService.findById(transaction.receiverId());

        userService.validateTransaction(sender, transaction.value());

        boolean authorize = authorizationTransaction(sender, transaction.value());
        if (!authorize) {
            throw new Exception("Transação não autorizada");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.value());
        newTransaction.setReceiver(receiver);
        newTransaction.setSender(sender);
        newTransaction.setTime(LocalDateTime.now());

        sender.setSaldo(sender.getSaldo().subtract(transaction.value()));
        receiver.setSaldo(receiver.getSaldo().add(transaction.value()));

        this.transactionRepository.save(newTransaction);
        this.userService.save(sender);
        this.userService.save(receiver);

        notificationService.sendNotification(sender, "Transação enviada com sucesso!");
        notificationService.sendNotification(receiver, "Transação recebida com sucesso!");

        return newTransaction;
    }

    public boolean authorizationTransaction(User sender, BigDecimal value) {

        try {
            ResponseEntity<Map> response = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                return true;
            }
            return false;
        } catch (HttpClientErrorException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
