package ru.hogwarts.school.serviceInterface;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface StudentService {

    Student createStudent(Student student);

    Student findStudent(Long id);

    Student editStudent(Student student);

    void deleteStudent(Long id);

//    Map<Long, Student> findStudentsByAge(Long id);

    Collection<Student> findByAgeBetween(int min, int max);

    Faculty findFacultyByStudent(Long id);

    Integer countOfStudentInSchool();

    Double averageAgeOfStudent();

    Collection<Student> getLastFiveStudents();

    List<String> getListOfStudentsOnA();

    Double getAverageAge();

    String printNamesParallel() throws ExecutionException, InterruptedException;

    String printNamesSynchronized();

}
