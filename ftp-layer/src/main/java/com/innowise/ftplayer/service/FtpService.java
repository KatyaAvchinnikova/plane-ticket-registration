package com.innowise.ftplayer.service;

import com.innowise.auditlayer.repository.MongoRepository;
import com.innowise.ftplayer.dto.FtpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FtpService {
   private final MongoRepository mongoRepository;

   public void storeImage(FtpRequest request) throws IOException {
      File file = new File(request.getPath());
//      Photo photo = new Photo();
//      String filename = item.getName();
//      if (filename == null || filename.length() == 0) {
//         log.error("img name illegal");
//         return null;
//      }
//      int index = filename.lastIndexOf(".");
//      String type = filename.substring(index + 1);
//      if (!ImgTools.checkImgFormatValidata(type)) {
//         log.error("img type illegal");
//         return null;
//      }
//      ObjectId id = ObjectIdGenerator.generate();
//      // filename = new ObjectId() + filename.substring(index);
//      photo.setId(id.toString());
//      photo.setType(type);
//
//      GridFS mphoto = new GridFS(MongoDBPool.getInstance().getDB(), collection);
//      GridFSInputFile in = null;
//      in = mphoto.createFile(item.getInputStream());
//      in.setId(id);
//      in.setFilename(id.toString());
//      in.setContentType(type);
//      in.save();
//      item.getInputStream().close();
//      return photo;

   }

}
