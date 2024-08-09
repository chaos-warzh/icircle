package github.chaoswarzh.icircle.controller;

import github.chaoswarzh.icircle.service.ImageService;
import github.chaoswarzh.icircle.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class ToolsController {
    @Autowired
    ImageService imageService;

    @PostMapping("/images")
    public ResultVO<String> upload(@RequestParam MultipartFile file){
        return ResultVO.buildSuccess(imageService.upload(file));
    }

}
