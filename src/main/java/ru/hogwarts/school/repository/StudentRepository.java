package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {

    public Collection<Student> findByAgeBetween(int min, int max);

    @Query(value = "SELECT count(*) as count FROM student", nativeQuery = true)
    Integer countOfStudentInSchool();


}
