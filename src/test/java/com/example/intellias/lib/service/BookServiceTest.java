package com.example.intellias.lib.service;

import com.example.intellias.lib.domain.Book;
import com.example.intellias.lib.dto.BookDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
@TestPropertySource("classpath:application.properties")
class BookServiceTest {

    @Autowired
    public BookService bookService;

    @Test
    void get_exist_book_by_title() {
        Book expectedBook = new Book();
        expectedBook.setId(1L);
        expectedBook.setTitle("War and Peace");
        expectedBook.setStudent(null);
        Book currentBook = bookService.getBookByTitle("War and Peace");
        assertEquals(expectedBook, currentBook);
    }

    @Test
    void exception_when_try_find_dose_not_exist_book() {
        assertThrows(IllegalArgumentException.class, () -> {
            bookService.getBookByTitle("Lord of thr Ring");
        });
    }

    @Test
    void get_book_by_id() {
        BookDto bookById = bookService.getBookById(2l);
        assertDoesNotThrow(() -> new IllegalArgumentException());
        assertEquals(2l, bookById.id());
    }

}