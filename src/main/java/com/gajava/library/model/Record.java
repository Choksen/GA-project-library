package com.gajava.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "record")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Record extends Base {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reader_id")
    private Reader reader;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;  // ???

    @Temporal(TemporalType.DATE)
    @Column(name = "date_receipt")
    private LocalDate dateReceipt;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_expected_return")
    private LocalDate dateExpectedReturn;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_valid_return")
    private LocalDate dateValidReturn;

    @Column
    private String comment;

}
