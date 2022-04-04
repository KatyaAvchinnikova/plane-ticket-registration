package com.innowise.ftplayer.service;

import com.innowise.ftplayer.messaging.MessageProducer;
import com.innowise.ftplayer.repository.PhotoRepository;
import com.innowise.message.FtpInfoMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FtpService {

    private final PhotoRepository photoRepo;
    private final MongoTemplate template;

    public String storeImage(FtpInfoMessage photo) throws IOException {
        photoRepo.insert(photo);
        return photo.getId();
    }

    public FtpInfoMessage getPhoto(String id) {
        return photoRepo.findFtpInfoMessageById(id);
    }

    public List<String> download(String email) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        return template.find(query, FtpInfoMessage.class).stream()
                       .map(k -> k.getId())
                       .collect(Collectors.toList());
    }

}
