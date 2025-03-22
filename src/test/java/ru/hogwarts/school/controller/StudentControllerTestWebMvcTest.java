package ru.hogwarts.school.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentServiceImpl;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = StudentController.class)
class StudentControllerTestWebMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRepository studentRepository;

    @SpyBean
    private StudentServiceImpl studentServiceImpl;

    @InjectMocks
    private StudentController studentController;

    @Test
    void getStudent() throws Exception {
        Long studentId = 1L;
        String studentName = "test";
        int studentAge = 20;

        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("Slizering");
        faculty.setColor("Green");


        Student student = new Student();
        student.setId(studentId);
        student.setName(studentName);
        student.setAge(studentAge);
        student.setFaculty(faculty);

        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/student/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value(studentName))
                .andExpect(jsonPath("$.age").value(studentAge))
                .andExpect(jsonPath("$.faculty.id").value(faculty.getId()))
                .andExpect(jsonPath("$.faculty.name").value(faculty.getName()))
                .andExpect(jsonPath("$.faculty.color").value(faculty.getColor()));


    }

    @Test
    void createStudent() {
    }

    @Test
    void findFacultyByStudent() {
    }

    @Test
    void editStudent() {
    }

    @Test
    void deleteStudent() {
    }

    @Test
    void getStudentByAgeList() {
    }

    @Test
    void getStudentByAgeBetweenList() {
    }
}