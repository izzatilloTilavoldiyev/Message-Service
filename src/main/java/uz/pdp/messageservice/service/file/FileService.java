package uz.pdp.messageservice.service.file;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.messageservice.dtos.UploadFileResponse;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class FileService {

    private final Path fileLocation;


    public FileService() {
        String fileUploadDir = "C:\\JAVA\\RealProjects\\Message-Service\\src\\main\\resources\\uploads";
        this.fileLocation = Paths.get(fileUploadDir)
                .toAbsolutePath().normalize();
    }

    public UploadFileResponse saveFile(MultipartFile file) {
        String fullFileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        try {
            Path targetLocation = fileLocation.resolve(fullFileName);
            Files.copy(file.getInputStream(), targetLocation);
        } catch (FileAlreadyExistsException e) {
            String[] fileNameAndType = fullFileName.split("\\.");
            fullFileName = fileNameAndType[0] + System.currentTimeMillis() + "." + fileNameAndType[1];
            Path targetLocation = fileLocation.resolve(fullFileName);
            try {
                Files.copy(file.getInputStream(), targetLocation);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return UploadFileResponse.builder()
                .fileName(file.getOriginalFilename())
                .fileType(file.getContentType())
                .fileDownloadUri(fileLocation + "\\" + file.getOriginalFilename())
                .size(file.getSize())
                .build();
    }

    public Path downloadFile(String fileName) {
        return fileLocation.resolve(fileName);
    }
}
