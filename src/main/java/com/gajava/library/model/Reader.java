package com.gajava.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "reader")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reader extends Person {

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "reader_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> books;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "number_reader", nullable = false, unique = true)
    private Long numberReader;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private Integer rating;


}
