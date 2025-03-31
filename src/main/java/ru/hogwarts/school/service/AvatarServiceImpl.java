package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.serviceInterface.AvatarService;

@Service
@Transactional
public class AvatarServiceImpl implements AvatarService {
    @Value("${avatar.cover.dir.path}")
    private String coversDir;

    private AvatarService avatarService;
    private AvatarRepository avatarRepository;


}
