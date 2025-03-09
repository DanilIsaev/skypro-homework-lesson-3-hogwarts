package ru.hogwarts.school.serviceInterface;

import ru.hogwarts.school.model.Faculty;

public interface FacultyService {

    Faculty createFaculty(Faculty faculty);

    Faculty findFaculty(Long lastId);

    Faculty editFaculty(Faculty faculty);

    Faculty deleteFaculty(Long id);
}
