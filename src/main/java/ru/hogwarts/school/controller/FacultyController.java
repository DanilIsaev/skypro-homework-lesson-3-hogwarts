package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyServiceImpl;
import ru.hogwarts.school.serviceInterface.FacultyService;

@RestController
@RequestMapping("faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyServiceImpl facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long id) {
        Faculty facultyFound = facultyService.findFaculty(id);
        if (facultyFound == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facultyFound);
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
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable Long id) {
        Faculty facultyFound = facultyService.deleteFaculty(id);
        if (facultyFound == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facultyFound);
    }
}
