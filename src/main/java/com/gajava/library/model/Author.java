package com.gajava.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "author",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"first_name", "last_name"})})
@Getter
@Setter
@AllArgsConstructor
public class Author extends Person {
}
