package com.example.intellias.lib.repository;

import com.example.intellias.lib.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    Optional<Book> findBookByTitle(String title);
    boolean deleteBookById(Long id);

}
