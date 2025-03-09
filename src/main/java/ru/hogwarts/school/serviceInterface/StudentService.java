package ru.hogwarts.school.serviceInterface;

import ru.hogwarts.school.model.Student;

public interface StudentService {

    Student createStudent(Student student);

    Student findStudent(Long id);

    Student editStudent(Student student);

    Student deleteStudent(Long id);

}
