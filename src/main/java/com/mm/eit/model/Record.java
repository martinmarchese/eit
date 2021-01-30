package com.mm.eit.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

import com.mm.eit.types.Concept;
import com.mm.eit.types.RecordType;
import lombok.*;

@Entity
@Table
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Record {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Account account;
    @Column
    private RecordType type;
    @Column
    private Date date;
    @Column
    private BigDecimal amount;
    @Column
    private Concept concept;
    @Column
    private String comments;

}
