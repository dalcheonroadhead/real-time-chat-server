package com.oauth2.practices3.S3Upload.Repository;

import com.oauth2.practices3.S3Upload.Entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
