package com.oauth2.practices3.S3Upload.Config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {

    private final String accessKey = "본인의 키를 집어넣으세요";

    private final String secretKey = "본인의 키를 집어넣으세요";


    private final String region ="본인의 지역을 집어넣으세요";

    // 1) 필요한 값들을 application.yml로부터 받아서 AmazonS3Client를 만드는 작업
    @Bean
    public AmazonS3Client amazonS3Client() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        return (AmazonS3Client) AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
    }

}
