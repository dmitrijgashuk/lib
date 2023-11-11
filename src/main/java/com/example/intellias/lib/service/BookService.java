package com.example.intellias.lib.service;

import com.example.intellias.lib.domain.Book;
import com.example.intellias.lib.dto.BookDto;
import com.example.intellias.lib.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("The book with ID: " + id + " was not found"));
        return new BookDto(book.getId(), book.getTitle(), book.getStudent());
    }

    public Book getBookByTitle(String title) {// todo bookDto
        return bookRepository.findBookByTitle(title)
                .orElseThrow(() -> new IllegalArgumentException("The book with Title: " + title + " was not found"));
    }

    public BookDto addBook(BookDto bookDto) {
        Book savedBook = bookRepository.save(new Book(bookDto.id(), bookDto.title(), bookDto.student()));
        return new BookDto(savedBook.getId(), savedBook.getTitle(), savedBook.getStudent());
    }

    public boolean remove(Long id) {
        return bookRepository.deleteBookById(id);
    }

    public BookDto update(BookDto book) {
        Book updatedBook = bookRepository.save(new Book(book.id(), book.title(), book.student()));
        return new BookDto(updatedBook.getId(), updatedBook.getTitle(), updatedBook.getStudent());
    }

    public Page<BookDto> getBooks(Pageable pageable) {
        Page<Book> bookPage = bookRepository.findAll(pageable);
        List<BookDto> collect = bookPage.get()
                .map(book ->
                    new BookDto(book.getId(), book.getTitle(), book.getStudent())
                ).collect(Collectors.toList());
        return new PageImpl<>(collect,pageable,bookPage.getTotalElements());
    }

}