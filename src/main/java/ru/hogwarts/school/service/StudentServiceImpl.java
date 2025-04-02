package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.serviceInterface.StudentService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class StudentServiceImpl implements StudentService {

    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

//    private final Map<Long, Student> students = new HashMap<>();

    public Student createStudent(Student student) {
        logger.info("Creating student");
        return studentRepository.save(student);
    }

    public Student findStudent(Long id) {
        logger.info("Finding student");
        return studentRepository.findById(id).get();
    }

    public Student editStudent(Student student) {
        logger.info("Updating student");
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        logger.info("Deleting student");
        studentRepository.deleteById(id);
    }


    public Faculty findFacultyByStudent(Long id) {
        logger.info("Finding faculty");
        return studentRepository.findById(id).get().getFaculty();
    }

    @Override
    public Integer countOfStudentInSchool() {
        logger.info("Counting student in school");
        return studentRepository.countOfStudentInSchool();
    }

    @Override
    public Double averageAgeOfStudent() {
        logger.info("Average age of student");
        return studentRepository.averageAgeOfStudent();
    }

    @Override
    public Collection<Student> getLastFiveStudents() {
        logger.info("Getting last five students");
        return studentRepository.getLastFiveStudents();
    }

    @Override
    public List<String> getListOfStudentsOnA() {
        return studentRepository.findAll().stream()
                .map(Student::getName)
                .filter(name -> name.startsWith("A") || name.startsWith("А")) // для английской и русской А
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public Double getAverageAge() {
        return studentRepository.findAll().stream()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0.0);
    }


    public Collection<Student> findByAgeBetween(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }

//        public Map<Long, Student> findStudentsByAge(Long targetAge) {
//        return students.values().stream()
//                .filter(student -> student.getAge() == targetAge)
//                .collect(Collectors.toMap(Student::getId, student -> student));
//    }


}
