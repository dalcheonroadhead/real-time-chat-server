package com.oauth2.practices3.S3Upload.Service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.oauth2.practices3.S3Upload.Dto.Request.ImageSaveDto;
import com.oauth2.practices3.S3Upload.Entity.Image;
import com.oauth2.practices3.S3Upload.Repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {
    private static String bucketName = "soomintest";

    private final AmazonS3Client amazonS3Client;
    private final ImageRepository imageRepository;

    // 1) 이미지 여러개 저장시 사용
    @Transactional
    public List<String> saveImages(ImageSaveDto saveDto){

        List<String> resultList = new ArrayList<>();

        for (MultipartFile multipartFile : saveDto.getImages()) {
            String value = saveImage(multipartFile);
            resultList.add(value);
        }

        return resultList;
    }


    // 2) 이미지 하나 저장 시 로직
    @Transactional
    public String saveImage(MultipartFile multipartFile) {

        // 이미지 원래 이름 받아오기.
        String originalName = multipartFile.getOriginalFilename();

        // 이미지 Entity 객체에 맞춰서 이미지 객체 만들기
        Image image = new Image(originalName);
        String filename = image.getStoredName();
        try {
            // S3 메타데이터 설정
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(multipartFile.getContentType());
            objectMetadata.setContentLength(multipartFile.getInputStream().available());

            // 클라이언트에 이미지를 넣는다.
            amazonS3Client.putObject(bucketName, filename, multipartFile.getInputStream(),objectMetadata);

            // 이미지 접근 URL을 받아온다.
            String accessUrl = amazonS3Client.getUrl(bucketName, filename).toString();

            // 이미지 접근 주소를 받는다.
            image.setAccessUrl(accessUrl);

            // 오류 핸들링
        }catch (IOException e){
            log.info("이미지 저장 과정에서 오류가 났습니다. {}", e.getMessage());
        }

        //  이미지 DB에도 저장
        imageRepository.save(image);

        // 이미지 접근 주소 반환
        return image.getAccessUrl();
    }

    ;
}
