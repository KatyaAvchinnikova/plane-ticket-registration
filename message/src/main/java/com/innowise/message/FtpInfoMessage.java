package com.innowise.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "Scans")
//@Getter
//@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FtpInfoMessage implements Serializable {

    @Id
    private String id;
    private String title;
    private Binary image;
    private String email;
    private String mimeType;

}
