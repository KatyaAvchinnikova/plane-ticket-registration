package com.innowise.ftplayer.domain;

import lombok.Getter;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Scans")
@Getter
@Setter
public class Photo {

    @Id
    private String id;
    private String title;
    private Binary image;
    private String email;
    private String mimeType;

    public Photo(String title, String email) {
        this.title = title;
        this.email = email;
    }

}
