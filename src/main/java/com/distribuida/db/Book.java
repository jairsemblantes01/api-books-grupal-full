package com.distribuida.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "books")
public class Book {
    @Id
    private String id;
    @Field("isbn")
    private String isbn;
    @Field("title")
    private String title;
    @Field("author")
    private Integer author;
    @Field("price")
    private Double price;
}
