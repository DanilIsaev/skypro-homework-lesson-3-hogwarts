package ru.hogwarts.school.serviceInterface;

import ru.hogwarts.school.model.Faculty;

import java.util.Map;

public interface FacultyService {

    Faculty createFaculty(Faculty faculty);

    Faculty findFaculty(Long lastId);

    Faculty editFaculty(Faculty faculty);

    void deleteFaculty(Long id);

    Map<Long, Faculty> findFacultyByColor(String color);
}
