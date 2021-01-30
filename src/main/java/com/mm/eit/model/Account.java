package com.mm.eit.model;


import java.math.BigDecimal;
import java.util.List;

import com.mm.eit.types.Currency;
import lombok.*;

import javax.persistence.*;


@Entity
@Table
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;
    @Column
    private String description;
    @Column
    private Currency currency;
    @Column
    private BigDecimal balance;
    @Column
    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Record> records;
}
