package com.project.hire.services;

import com.project.hire.exceptions.NotFoundException;
import com.project.hire.models.File;
import com.project.hire.repositories.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;

    public File getFile(long id) {
        return fileRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    public File saveFile(byte[] content) {
        File file = new File();
        file.setContent(content);

        return fileRepository.save(file);
    }

    public File saveFile(String content) {
        byte[] bytes = new byte[content.length()];
        for (int i = 0; i < content.length(); i++) {
            bytes[i] = (byte) content.charAt(i);
        }

        return saveFile(bytes);
    }
}
