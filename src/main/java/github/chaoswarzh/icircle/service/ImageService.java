package github.chaoswarzh.icircle.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String upload(MultipartFile file);
}
