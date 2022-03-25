package com.innowise.ftplayer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Document(collection = "Scans")
@Getter
@Setter
public class Photo {
    @Id
    private String id;
    private String title;
    private Binary image;

    public Photo(String title) {
        this.title = title;
    }

}
