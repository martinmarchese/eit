package com.mm.eit.controller;

import com.mm.eit.model.Account;
import com.mm.eit.model.Record;
import com.mm.eit.repository.AccountRepository;
import com.mm.eit.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RecordController {

    @Autowired
    RecordRepository recordRepository;

    @GetMapping("/records")
    public ResponseEntity<List<Record>> getAllRecords() {
        try {
            List<Record> records = new ArrayList<Record>();

            recordRepository.findAll().forEach(records::add);

            if (records.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(records, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/records/{id}")
    public ResponseEntity<Record> getrecordById(@PathVariable("id") long id) {
        Optional<Record> recordData = recordRepository.findById(id);

        if (recordData.isPresent()) {
            return new ResponseEntity<>(recordData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/records")
    public ResponseEntity<Record> createRecord(@RequestBody Record record) {
        try {
            Record _record = recordRepository
                    .save(Record.builder()
                            .account(record.getAccount())
                            .comments(record.getComments())
                            .amount(record.getAmount())
                            .concept(record.getConcept())
                            .build());
            return new ResponseEntity<>(_record, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/records/{id}")
    public ResponseEntity<Record> updateRecord(@PathVariable("id") long id, @RequestBody Record record) {
        Optional<Record> recordData = recordRepository.findById(id);

        if (recordData.isPresent()) {
            Record _record = recordData.get();
            _record.setAccount(record.getAccount());
            _record.setComments(record.getComments());
            _record.setAmount(record.getAmount());
            _record.setConcept(record.getConcept());
            return new ResponseEntity<>(recordRepository.save(_record), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/records/{id}")
    public ResponseEntity<HttpStatus> deleteRecord(@PathVariable("id") long id) {
        try {
            recordRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/records")
    public ResponseEntity<HttpStatus> deleteAllRecords() {
        try {
            recordRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
