package com.gajava.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "author",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"first_name", "last_name"})})
@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
public class Author extends Person {
   /* @ManyToMany
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Book> books;*/
}
