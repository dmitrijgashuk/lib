package com.example.intellias.lib.service;

import com.example.intellias.lib.dto.StudentDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource("/application.properties")
class StudentServiceTest {

    @Autowired
    public StudentService studentService;

    @Test
    void fetchStudentById() {
        StudentDto expectedDto = new StudentDto(1L,"Oleg","Moroz",new ArrayList<>());
        StudentDto currentDto = studentService.fetchStudentById(1L);
        assertEquals(expectedDto, currentDto);
    }

    @Test
    void delete_student_by_sd(){
        StudentDto studentDto = studentService.fetchStudentById(2l);
        assertEquals(2L,studentDto.id());
        studentService.deleteStudentById(2l);
        assertThrows(IllegalArgumentException.class, () -> {
            studentService.fetchStudentById(2L);
        });
    }

}