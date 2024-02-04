package com.oauth2.practices3.S3Upload.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="image_id")
    private Long id;

    private String originName;

    private String storedName;

    @Setter
    private String accessUrl;

    public Image(String originName){
        this.originName = originName;
        this.storedName = getFileName(originName);
        this.accessUrl = "";
    }

    // 이미지 파일의 확장자를 추출하는 메소드
    public String extractExtension(String originName){
        int index = originName.lastIndexOf('.');

        return originName.substring(index, originName.length());
    }

    // 이미지 파일의 이름을 저장하기 위한 이름으로 변환하는 메소드
    public String getFileName(String originName){
        return UUID.randomUUID() + "." + extractExtension(originName);
    }


}
