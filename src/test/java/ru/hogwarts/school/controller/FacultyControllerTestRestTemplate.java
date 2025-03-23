package ru.hogwarts.school.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.model.Faculty;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FacultyControllerTestRestTemplate {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private FacultyController facultyController;

    //Тест, который проверяет что все бины были проинициализированы
    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(facultyController).isNotNull();
    }

    @Test
    void getFaculty() {
        Faculty facultyTest = new Faculty();
        facultyTest.setId(1L);
        facultyTest.setName("Slizering");
        facultyTest.setColor("Green");

        Assertions
                .assertThat(restTemplate.getForObject("http://localhost:" + port + "/faculty", Faculty.class))
                .isNotNull();

        Assertions
                .assertThat(restTemplate.getForObject("http://localhost:" + port + "/faculty/1", Faculty.class))
                .isEqualTo(facultyTest);
    }

    @Test
    void getFacultyByStudentId() {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/1/student", Faculty.class))
                .isNotNull();
    }

    @Test
    void getFacultyByColorListIgnoreCase() {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/search_color", Faculty.class))
                .isNotNull();
    }
}