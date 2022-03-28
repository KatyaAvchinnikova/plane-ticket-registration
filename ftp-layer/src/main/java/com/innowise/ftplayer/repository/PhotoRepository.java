package com.innowise.ftplayer.repository;

import com.innowise.ftplayer.domain.Photo;
import com.innowise.message.FtpInfoMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends MongoRepository<FtpInfoMessage, String> {
    public List<Photo> findPhotoByEmail(String email);
}
