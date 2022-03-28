package com.innowise.ftplayer.service;

import com.innowise.ftplayer.repository.PhotoRepository;
import com.innowise.message.FtpInfoMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FtpService {

    private final PhotoRepository photoRepo;

    public String storeImage(FtpInfoMessage photo) throws IOException {
        photoRepo.insert(photo);
        return photo.getId();
    }

    public FtpInfoMessage getPhoto(String id) {
        return photoRepo.findById(id).orElseThrow(() -> new RuntimeException(""));
    }

}
