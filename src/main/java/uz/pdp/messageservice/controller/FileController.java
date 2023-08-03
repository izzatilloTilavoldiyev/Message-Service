package uz.pdp.messageservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.messageservice.dtos.UploadFileResponse;
import uz.pdp.messageservice.service.file.FileService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/api/v1/file/")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    /**
     *
     * @param file -> Multipart request file which contains the input send from user
     * @return UploadFileResponse which includes download url and file name
     */
    @PostMapping("/upload")
    public UploadFileResponse uploadFile(@RequestParam MultipartFile file) {
        return fileService.saveFile(file);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        Path file = fileService.downloadFile(fileName);
        try {
            String mediaType = Files.probeContentType(file);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(mediaType))
                    .body(new UrlResource(file.toUri()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
