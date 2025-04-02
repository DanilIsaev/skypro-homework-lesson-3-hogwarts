package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.serviceInterface.StudentService;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;

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

    @GetMapping("/count")
    public Integer countOfStudentInSchool() {
        return studentService.countOfStudentInSchool();
    }

    @GetMapping("/average_age_sql")
    public Double averageAgeOfStudentInSchool() {
        return studentService.averageAgeOfStudent();
    }

    @GetMapping("/last_five_student")
    public Collection<Student> getLastFiveStudents() {
        return studentService.getLastFiveStudents();
    }

    @GetMapping("/get_list_of_students_on_A")
    public List<String> getListOfStudentsOnA() {
        return studentService.getListOfStudentsOnA();
    }

    @GetMapping("/average-age")
    public double getAverageAge() {
        return studentService.getAverageAge();
    }

    @GetMapping("/print-parallel")
    public String printNamesParallel() throws ExecutionException, InterruptedException {
        return studentService.printNamesParallel();
    }

    @GetMapping("/print-synchronized")
    public String printNamesSynchronized() {
        return studentService.printNamesSynchronized();
    }



}
