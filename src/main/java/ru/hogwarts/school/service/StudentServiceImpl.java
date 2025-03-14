package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.serviceInterface.StudentService;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    private final Map<Long, Student> students = new HashMap<>();

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudent(Long id) {
        return studentRepository.findById(id).get();
    }

    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
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
