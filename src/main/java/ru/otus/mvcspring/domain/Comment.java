package ru.otus.mvcspring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Comment {

    @Id
    private String id;

    private String comment;

    public Comment(String comment) {
        this.comment = comment;
    }
}
