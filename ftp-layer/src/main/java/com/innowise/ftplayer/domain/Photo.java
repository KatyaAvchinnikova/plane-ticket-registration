package com.innowise.ftplayer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "Scans")
@Getter
@Setter
@AllArgsConstructor
public class Photo implements Serializable {

    @Id
    private String id;
    private String title;
    private Binary image;
    private String email;
    private String mimeType;

}
