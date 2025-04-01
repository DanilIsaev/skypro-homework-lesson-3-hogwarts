package ru.hogwarts.school.serviceInterface;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentService {

    Student createStudent(Student student);

    Student findStudent(Long id);

    Student editStudent(Student student);

    void deleteStudent(Long id);

//    Map<Long, Student> findStudentsByAge(Long id);

    Collection<Student> findByAgeBetween(int min, int max);

    Faculty findFacultyByStudent(Long id);

    Integer countOfStudentInSchool();

}
