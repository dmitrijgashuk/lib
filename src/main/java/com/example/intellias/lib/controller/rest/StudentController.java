package com.example.intellias.lib.controller.rest;

import com.example.intellias.lib.domain.Student;
import com.example.intellias.lib.dto.StudentDto;
import com.example.intellias.lib.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public ResponseEntity<Page<Student>> getPageStudents(){
        return null;
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id){
        StudentDto studentDto = studentService.fetchStudentById(id);
        return new ResponseEntity<>(studentDto, HttpStatus.OK) ;
    }

    @PostMapping("/students")
    public ResponseEntity<StudentDto> addNewStudent(@RequestBody Student student){
        StudentDto studentDto = studentService.createNewStudent(student);
        return new ResponseEntity<>(studentDto,HttpStatus.CREATED);
    }

    @PutMapping("/students")
    public ResponseEntity<StudentDto> updateStudent(@RequestBody StudentDto studentDto){
        StudentDto updateStudentDto = studentService.updateStudent(studentDto);
        return new ResponseEntity<>(updateStudentDto,HttpStatus.OK);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudent(Long id){
        studentService.deleteStudentById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
