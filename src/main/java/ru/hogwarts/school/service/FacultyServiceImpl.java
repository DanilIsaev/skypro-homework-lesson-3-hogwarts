package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.serviceInterface.FacultyService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    private final Map<Long, Faculty> faculties = new HashMap<>();

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(Long findId) {
        return facultyRepository.findById(findId).get();
    }

    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }

    public Map<Long, Faculty> findFacultyByColor(String findColor) {
        return faculties.values().stream()
                .filter(faculty -> faculty.getColor().equals(findColor))
                .collect(Collectors.toMap(Faculty::getId, faculty -> faculty));
    }

    public Collection<Faculty> findFacultyByColorIgnoreCase(String findColor) {
        return facultyRepository.findByColorIgnoreCase(findColor);
    }

}
