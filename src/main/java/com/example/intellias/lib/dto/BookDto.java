package com.example.intellias.lib.dto;

import com.example.intellias.lib.domain.Student;

public record BookDto(Long id, String title, Student student) {
}
