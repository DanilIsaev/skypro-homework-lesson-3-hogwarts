package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyServiceImpl;
import ru.hogwarts.school.serviceInterface.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyServiceImpl facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> editFaculty(Faculty faculty) {
        Faculty facultyFound = facultyService.editFaculty(faculty);
        if (facultyFound == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facultyFound);
    }

    @DeleteMapping("{id}")
    public void deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);

    }

    @GetMapping("{id}/student")
    public Collection<Student> getFacultyByStudentId(@PathVariable Long id) {
        return facultyService.findStudentsByFacultyId(id);
    }


    @GetMapping("/search_color")
    public Collection<Faculty> getFacultyByColorListIgnoreCase(@RequestParam String color) {
        return facultyService.findFacultyByColorIgnoreCase(color);
    }

//    @GetMapping("/search_color/{color}")
//    public Map<Long, Faculty> getFacultyByColorList(@PathVariable String color) {
//        return facultyService.findFacultyByColor(color);
//    }
}
