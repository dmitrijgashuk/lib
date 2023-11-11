package com.example.intellias.lib.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(generator = "book_seq")
    @SequenceGenerator(name = "book_seq", sequenceName = "books_id_seq", allocationSize = 1)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @ManyToOne
    /*@JoinColumn(name = "student_id")*/
    private Student student;

}
