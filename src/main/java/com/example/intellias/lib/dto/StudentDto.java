package com.example.intellias.lib.dto;

import com.example.intellias.lib.domain.Book;

import java.util.List;

public record StudentDto(Long id, String firstName, String lastName, List<Book> books) {
}
