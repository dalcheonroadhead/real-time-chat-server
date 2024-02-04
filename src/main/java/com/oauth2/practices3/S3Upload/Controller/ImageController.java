package com.oauth2.practices3.S3Upload.Controller;

import com.oauth2.practices3.S3Upload.Dto.Request.ImageSaveDto;
import com.oauth2.practices3.S3Upload.Service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/image")
    @ResponseStatus(HttpStatus.OK)
    //DTO로 이미지 데이터를 받아온다. 이를 통하여 로직을 수행한다.
    public List<String> saveImage(@ModelAttribute ImageSaveDto imageSaveDto){
        return imageService.saveImages(imageSaveDto);
    }

}
