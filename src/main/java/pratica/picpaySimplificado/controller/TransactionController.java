package pratica.picpaySimplificado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pratica.picpaySimplificado.domain.Transaction;
import pratica.picpaySimplificado.dtos.RequestTransaction;
import pratica.picpaySimplificado.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody RequestTransaction dto) throws Exception {
        Transaction transaction = transactionService.createTransaction(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
    }
}
