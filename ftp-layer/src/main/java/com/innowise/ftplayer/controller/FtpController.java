package com.innowise.ftplayer.controller;

import com.innowise.ftplayer.service.FtpService;
import com.innowise.message.FtpInfoMessage;
import com.innowise.message.dto.DtoImage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Tag(name = "FTP controller")
public class FtpController {

    private final FtpService ftpService;

    @GetMapping("/{id}/files")
    @Operation(summary = "Show list of images belong to user")
    public List<String> download(@PathVariable Long id, @RequestParam String email) {
        return ftpService.download(email);
    }

    @GetMapping("/file/")
    @Operation(summary = "Download image")
    public DtoImage getPhoto(@RequestParam String id) {
        FtpInfoMessage photo = ftpService.getPhoto(id);
        return DtoImage.builder().mimeType(photo.getMimeType())
                       .title(photo.getTitle())
                       .image(photo.getImage().getData())
                       .id(photo.getId()).build();

    }

    @GetMapping(value = "/download/", consumes = MediaType.ALL_VALUE,
                produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @Operation(summary = "Download image")
    public ResponseEntity<?> getPhotoNew(@RequestParam String id) {
        var image = ftpService.getPhoto(id);
        return ResponseEntity.ok()
                             .contentType(MediaType.parseMediaType(image.getMimeType()))
                             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
                                     + image.getTitle() + "\"")
                             .body(new ByteArrayResource(image.getImage().getData()));
    }

}
