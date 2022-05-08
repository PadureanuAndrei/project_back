package com.project.hire.controllers;

import com.project.hire.models.File;
import com.project.hire.services.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/files")
@CrossOrigin
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<byte[]> getFile(@PathVariable long id) {
        File file = fileService.getFile(id);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(file.getContent());
    }

}
