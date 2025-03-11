package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.serviceInterface.StudentService;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class StudentServiceImpl implements StudentService {

    private final Map<Long, Student> students = new HashMap<>();
    private static Long lastId = 0L;

    public Student createStudent(Student student) {
        students.put(++lastId, student);
        student.setId(lastId);
        return student;
    }

    public Student findStudent(Long id) {
        return students.get(id);
    }

    public Student editStudent(Student student) {
        if (students.containsKey(student.getId())) {
            students.put(student.getId(), student);
            return student;
        }
        return null;
    }

    public Student deleteStudent(Long id) {
        return students.remove(id);
    }

    public Map<Long, Student> findStudentsByAge(Long targetAge) {
        return students.values().stream()
                .filter(student -> student.getAge() == targetAge)
                .collect(Collectors.toMap(Student::getId, student -> student));
    }

//    public Map<Long, Student> findListAge(Integer findAge) {
//        Map<Long, Student> studentsFindAge = new HashMap<>();;
//        for (Student student : students.values()) {
//            if (findAge == student.getAge()) {
//                studentsFindAge.put(student.getId(),student);
//            }
//        }
//        return studentsFindAge;
//    }

}
