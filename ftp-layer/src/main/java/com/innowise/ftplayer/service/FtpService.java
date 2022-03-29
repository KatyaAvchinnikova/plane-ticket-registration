package com.innowise.ftplayer.service;

import com.innowise.ftplayer.messaging.MessageProducer;
import com.innowise.ftplayer.repository.PhotoRepository;
import com.innowise.message.EmailMessage;
import com.innowise.message.FtpInfoMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FtpService {

    private final PhotoRepository photoRepo;
    private final MongoTemplate template;
    private final MessageProducer producer;

    public String storeImage(FtpInfoMessage photo) throws IOException {
        photoRepo.insert(photo);
        return photo.getId();
    }

    public FtpInfoMessage getPhoto(String id) {
        return photoRepo.findById(id).orElseThrow(() -> new RuntimeException(""));
    }

    public void download(EmailMessage emailMessage) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(emailMessage.getEmail()));
        List<FtpInfoMessage> photos = template.find(query, FtpInfoMessage.class);
        for (FtpInfoMessage photo : photos) {
            producer.store(photo);
        }
    }

}
