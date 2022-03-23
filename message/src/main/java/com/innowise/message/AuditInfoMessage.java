package com.innowise.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "Emails")
public class AuditInfoMessage implements Serializable {

    @Id
    private String id;

    @Indexed(unique = true)
    @Field(value = "email")
    private String email;

    private String userName;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

}
