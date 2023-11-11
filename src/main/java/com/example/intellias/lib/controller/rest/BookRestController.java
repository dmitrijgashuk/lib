package com.example.intellias.lib.controller.rest;

import com.example.intellias.lib.dto.BookDto;
import com.example.intellias.lib.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController()
@Tag(name = "Book", description = "BookApi")
public class BookRestController {

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @Operation(summary = "Get one page of books")
    @GetMapping("/books")
    public ResponseEntity<Page<BookDto>> getBooks(@RequestParam(required = false) Pageable pageable) {
        if (pageable == null) {
            pageable = Pageable.ofSize(5);
        }
        Page<BookDto> books = bookService.getBooks(pageable);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @Operation(summary = "Fetch book by Id")
    @GetMapping("/books/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @Operation(summary = "Add new book to library")
    @PostMapping("/books")
    public ResponseEntity<BookDto> addNewBook(@RequestBody BookDto bookDto) {
        return ResponseEntity.ok(bookService.addBook(bookDto));
    }

    @PutMapping("/books")
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto book) {
        BookDto update = bookService.update(book);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @Operation(summary = "Delete one book by Id")
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        boolean isDelete = bookService.remove(id);
        if (!isDelete) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
