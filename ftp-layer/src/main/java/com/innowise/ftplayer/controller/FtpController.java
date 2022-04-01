package com.innowise.ftplayer.controller;

import com.innowise.ftplayer.service.FtpService;
import com.innowise.message.FtpInfoMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ftp")
@Api("FTP controller")
public class FtpController {

    private final FtpService ftpService;

//    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
//    @ApiOperation("Store file")
//    public ResponseEntity<String> store(@RequestParam("title") String title,
//            @RequestPart("image") MultipartFile image, @RequestParam String email) throws IOException {
//        String id = ftpService.storeImage(title, image, email);
//        return new ResponseEntity<>("Your file is stored successfully", HttpStatus.CREATED);
//    }

    @GetMapping(value = "/{id}/files")
    @ApiOperation("Download file")
    public List<FtpInfoMessage> getPhoto(@PathVariable String id, String email) throws IOException {
        return ftpService.download(email);
//        return ResponseEntity.ok()
//                             .contentType(MediaType.parseMediaType("image/x-png"))
//                             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
//                                     + photo.getTitle() +
//                                     "\"")
//                             .body(new ByteArrayResource(photo.getImage().getData()));
    }

}
