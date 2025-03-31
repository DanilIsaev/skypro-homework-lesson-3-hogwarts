package ru.hogwarts.school.serviceInterface;

import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;

import java.io.IOException;

public interface AvatarService {
    Avatar uploadAvatar(Long id,MultipartFile file) throws IOException;
}
