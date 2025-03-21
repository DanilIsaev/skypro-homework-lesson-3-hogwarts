package ru.hogwarts.school.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTestRestTemplate {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private StudentController studentController;

    //Тест, который проверяет что все бины были проинициализированы
    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    void getStudent() {
        Faculty facultyTest = new Faculty();
        facultyTest.setId(1L);
        facultyTest.setName("Slizering");
        facultyTest.setColor("Green");

        Student studentTest = new Student();
        studentTest.setId(1L);
        studentTest.setName("Ron");
        studentTest.setAge(19);
        studentTest.setFaculty(facultyTest);

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/1", Student.class))
                .isNotNull();
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/1", Student.class))
                .isEqualTo(studentTest);
    }

    @Test
    void findFacultyByStudent() {
        Faculty facultyTest = new Faculty();
        facultyTest.setId(1L);
        facultyTest.setName("Slizering");
        facultyTest.setColor("Green");

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/1/faculty", Faculty.class))
                .isNotNull();

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/1/faculty", Faculty.class))
                .isEqualTo(facultyTest);
    }

    @Test
    void getStudentByAgeList() {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/20", Student.class))
                .isNotNull();
    }

    @Test
    void getStudentByAgeBetweenList() {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/?min=19&max=20", Student.class))
                .isNotNull();
    }
}