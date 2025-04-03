package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.serviceInterface.AvatarService;
import ru.hogwarts.school.serviceInterface.StudentService;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarServiceImpl implements AvatarService {

    @Value("${avatar.cover.dir.path}")
    private String coversDir;

    private AvatarService avatarService;

    @Autowired
    private AvatarRepository avatarRepository;

    private final StudentService studentService;

    public AvatarServiceImpl(StudentService studentService) {
        this.studentService = studentService;
    }

    Logger logger = LoggerFactory.getLogger(AvatarServiceImpl.class);

    @Override
    public void uploadAvatar(Long id, MultipartFile file) throws IOException {
        Student student = studentService.findStudent(id);

        logger.debug("Файл получен");

        Path filePath = Path.of(coversDir, id + "." + getException(file.getOriginalFilename()));
        Files.createDirectories(filePath.getParent()); // Проверяет есть ли необходимые директории для файла
        Files.deleteIfExists(filePath); // Проверяет наличие файла и в случае, если файл существует, то удаляет его предыдущий экземпляр


        try (InputStream is = file.getInputStream(); // Входной поток для считывания
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW); // Создаем пустой файл для записи в выходном потоке
             BufferedInputStream bis = new BufferedInputStream(is, 1024); // Размер входного потока для работы с файлом
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024); // Размер выходного потока для работы с файлом
        ) {
            bis.transferTo(bos);
        }

        Avatar avatar = findAvatar(id);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setData(generateImagePreview(filePath));

        avatarRepository.save(avatar);
        logger.debug("Файл сохранен");
    }

    private byte[] generateImagePreview(Path filePath) throws IOException {
        try (InputStream is = Files.newInputStream(filePath);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            BufferedImage image = ImageIO.read(bis);

            int height = image.getHeight() / (image.getWidth() / 100);
            BufferedImage preview = new BufferedImage(100, height, image.getType());
            Graphics2D g = preview.createGraphics();
            g.drawImage(image, 0, 0, 100, height, null);
            g.dispose();

            ImageIO.write(preview, getException(filePath.getFileName().toString()), bos);
            logger.debug("Превью для изображения создано");
            return bos.toByteArray();
        }
    }

    public Avatar findAvatar(Long studentId) {
        return avatarRepository.findByStudent_Id(studentId).orElse(new Avatar());
    }

    private String getException(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public Collection<Avatar> getAllAvatars(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page-1, size);
        return avatarRepository.findAll(pageRequest).getContent();
    }
}
