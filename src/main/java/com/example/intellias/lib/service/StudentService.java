package com.example.intellias.lib.service;

import com.example.intellias.lib.domain.Book;
import com.example.intellias.lib.domain.Student;
import com.example.intellias.lib.dto.StudentDto;
import com.example.intellias.lib.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public StudentDto fetchStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Student by ID: " + id + " dose not found"));
        List<Book> studentBooks = List.copyOf(student.getBooks());
        return new StudentDto(student.getId(),
                student.getFirstName(),
                student.getLastName(),
                studentBooks);
    }

    @Transactional
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    @Transactional
    public StudentDto createNewStudent(Student student) {
        Student newStudent = studentRepository.save(student);
        List<Book> studentBooks = List.copyOf(newStudent.getBooks());
        return new StudentDto(newStudent.getId(),
                newStudent.getFirstName(),
                newStudent.getLastName(),
                studentBooks);
    }

    @Transactional
    public StudentDto updateStudent(StudentDto studentDto){
        Student updatedStudent = studentRepository.save(new Student(studentDto.id(),
                studentDto.firstName(),studentDto.lastName(),studentDto.books()));
        List<Book> studentBooks = List.copyOf(updatedStudent.getBooks());
        return new StudentDto(updatedStudent.getId(),
                updatedStudent.getFirstName(),
                updatedStudent.getLastName(),
                studentBooks);
    }

}
