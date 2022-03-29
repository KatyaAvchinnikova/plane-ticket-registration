package com.innowise.ftplayer.repository;

import com.innowise.message.FtpInfoMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends MongoRepository<FtpInfoMessage, String> {
    public List<FtpInfoMessage> findPhotoByEmail(String email);
}
