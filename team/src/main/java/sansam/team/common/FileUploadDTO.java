package sansam.team.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class FileUploadDTO {

    private MultipartFile file;
}