package com.innowise.ftplayer.controller;

import com.innowise.ftplayer.domain.Photo;
import com.innowise.ftplayer.service.FtpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ftp")
@Api("FTP controller")
public class FtpController {

    private final FtpService ftpService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation("Store file")
    public ResponseEntity<String> store(@RequestParam("title") String title,
            @RequestPart("image") MultipartFile image, Model model) throws IOException {
        String id = ftpService.storeImage(title, image);
        return new ResponseEntity<>("Your file is stored successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getPhoto(@PathVariable String id, Model model) {
        Photo photo = ftpService.getPhoto(id);
        model.addAttribute("title", photo.getTitle());
        model.addAttribute("image", Base64.getEncoder().encodeToString(photo.getImage().getData()));
        return new ResponseEntity<>(Base64.getEncoder().encodeToString(photo.getImage().getData()), HttpStatus.OK);
//        return ResponseEntity.ok()
//                             .contentType(MediaType.parseMediaType(photo.getTitle()))
//                             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
//                                     + photo.getTitle() +
//                                     "\"")
//                             .body(new ByteArrayResource(photo.getImage().getData()));
    }

}
