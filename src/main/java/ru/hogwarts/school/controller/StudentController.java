package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.serviceInterface.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        Student studentFound = studentService.findStudent(id);
        if (studentFound == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentFound);
    }

    @GetMapping("{id}/faculty")
    public ResponseEntity<Faculty> findFacultyByStudent(@PathVariable Long id) {
        Student studentFound = studentService.findStudent(id);
        if (studentFound == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentService.findFacultyByStudent(id));
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(Student student) {
        Student studentFound = studentService.editStudent(student);
        if (studentFound == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentFound);
    }

    @DeleteMapping("{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

//    @GetMapping("{age}")
//    public Map<Long, Student> getStudentByAgeList(@PathVariable Long age) {
//        return studentService.findStudentsByAge(age);
//    }

    @GetMapping(params = {"min", "max"})
    public Collection<Student> getStudentByAgeBetweenList(@RequestParam(required = false) int min, @RequestParam(required = false) int max) {
        return studentService.findByAgeBetween(min, max);
    }

}
