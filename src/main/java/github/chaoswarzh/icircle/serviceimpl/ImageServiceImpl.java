package github.chaoswarzh.icircle.serviceimpl;

import github.chaoswarzh.icircle.exception.ICircleException;
import github.chaoswarzh.icircle.service.ImageService;
import github.chaoswarzh.icircle.util.OssUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * upload files
 */
@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    OssUtil ossUtil;

    @Override
    public String upload(MultipartFile file) {
        try {
            return ossUtil.upload(file.getOriginalFilename(),file.getInputStream());
        }catch (Exception e){
            e.printStackTrace();
            throw ICircleException.fileUploadFail();
        }
    }
}
