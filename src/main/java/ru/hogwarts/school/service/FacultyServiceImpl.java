package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.serviceInterface.FacultyService;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final Map<Long, Faculty> faculties = new HashMap<>();
    private static Long lastId = 0L;

    public Faculty createFaculty(Faculty faculty) {
        faculties.put(++lastId, faculty);
        faculty.setId(lastId);
        return faculty;
    }

    public Faculty  findFaculty(Long lastId) {
        return faculties.get(lastId);
    }

    public Faculty editFaculty(Faculty faculty) {
        if (faculties.containsKey(faculty.getId())) {
            faculties.put(faculty.getId(), faculty);
            return faculty;
        }
        return null;
    }

    public Faculty deleteFaculty(Long id) {
        return faculties.remove(id);
    }

    public Map<Long, Faculty> findFacultyByColor(String findColor) {
        return faculties.values().stream()
                .filter(faculty -> faculty.getColor().equals(findColor))
                .collect(Collectors.toMap(Faculty::getId, faculty -> faculty));
    }


}
