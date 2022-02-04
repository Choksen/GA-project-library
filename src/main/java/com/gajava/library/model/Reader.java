package com.gajava.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "reader")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reader extends Person {

    @OneToMany
    private Set<Record> records;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name="number_reader", nullable = false)
    private Long numberReader;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private Integer rating;


}
