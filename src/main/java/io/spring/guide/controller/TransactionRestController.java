package io.spring.guide.controller;

import io.spring.guide.model.dto.TransactionTO;
import io.spring.guide.service.modelbased.ITransactionModelService;
import io.spring.guide.service.modelbased.impl.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * "default comment"
 *
 * @author Anton Kurako (anku0817)
 * @since 16.10.2017
 */
@RestController
public class TransactionRestController {

    private final ITransactionModelService transService;

    @Autowired
    public TransactionRestController(TransactionService transService) {
        this.transService = transService;
    }

    @PostMapping(value = "/api/trans")
    public ResponseEntity<TransactionTO> create(@RequestParam String address,
                                                @RequestParam Integer amount) {
        final TransactionTO trans = TransactionTO.of(transService.create(address, amount));
        return (trans != null)
                ? new ResponseEntity<>(trans, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/api/trans/{id}")
    public ResponseEntity<TransactionTO> find(@PathVariable String id) {
        final TransactionTO trans = TransactionTO.of(transService.find(id));
        return (trans != null)
                ? new ResponseEntity<>(trans, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "api/trans/all")
    public ResponseEntity<List<TransactionTO>> findAll() {
        final List<TransactionTO> transList = transService.findAll()
                .stream()
                .map(TransactionTO::of)
                .collect(Collectors.toList());

        return (!transList.isEmpty())
                ? new ResponseEntity<>(transList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
