package com.innowise.message.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DtoImage {
    private String id;
    private String title;
    private byte[] image;
    private String email;
    private String mimeType;

}
