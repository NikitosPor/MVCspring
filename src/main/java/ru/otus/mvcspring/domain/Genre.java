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
public class Genre {

    @Id
    private String id;

    private String title;

    public Genre(String title) {
        this.title = title;
    }
}
