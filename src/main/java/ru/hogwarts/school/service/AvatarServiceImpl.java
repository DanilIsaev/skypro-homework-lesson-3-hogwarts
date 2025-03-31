package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.serviceInterface.AvatarService;
import ru.hogwarts.school.serviceInterface.StudentService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.substring;

@Service
@Transactional
public class AvatarServiceImpl implements AvatarService {
    @Value("${avatar.cover.dir.path}")
    private String coversDir;

    private AvatarService avatarService;
    private AvatarRepository avatarRepository;
    private final StudentService studentService;

    public AvatarServiceImpl(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public Avatar uploadAvatar(Long id, MultipartFile file) throws IOException {
        Student student = studentService.findStudent(id);

        Path filePath = Path.of(coversDir, id + "." + getException(Objects.requireNonNull(file.getOriginalFilename())));
        Files.createDirectories(filePath.getParent()); // Проверяет есть ли необходимые директории для файла
        Files.deleteIfExists(filePath); // Проверяет наличие на файла и в случае, если файл существует удаляет его предыдущий экземпляр


    }

    private String getException(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
