package com.innowise.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "Scans")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FtpInfoMessage implements Serializable {

    @Id
    private String id;
    private String title;
    private Binary image;
    private String email;
    private String mimeType;

    public FtpInfoMessage(String title, String email) {
        this.title = title;
        this.email = email;
    }

}
