package com.mm.eit.model;

import javax.persistence.*;
import java.math.BigDecimal;
import com.mm.eit.types.Currency;
import lombok.*;

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
    @OneToMany(mappedBy = "record", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Record record;
}