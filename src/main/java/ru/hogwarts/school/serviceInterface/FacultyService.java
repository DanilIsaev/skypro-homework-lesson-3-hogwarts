package ru.hogwarts.school.serviceInterface;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface FacultyService {

    Faculty createFaculty(Faculty faculty);

    Faculty findFaculty(Long lastId);

    Faculty editFaculty(Faculty faculty);

    void deleteFaculty(Long id);

//    Map<Long, Faculty> findFacultyByColor(String color);

    Collection<Faculty> findFacultyByColorIgnoreCase(String findColor);

    Collection<Student> findStudentsByFacultyId(Long facultyId);

}
