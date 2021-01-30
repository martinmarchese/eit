package com.mm.eit.repository;

import java.util.Date;
import java.util.List;

import com.mm.eit.model.Account;
import com.mm.eit.model.Record;
import com.mm.eit.types.Concept;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Long> {
    List<Account> findByDate(Date date);
    List<Account> findByConcept(Concept concept);
}
