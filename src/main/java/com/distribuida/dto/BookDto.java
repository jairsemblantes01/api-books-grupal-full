package com.distribuida.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
private String id;
private String isbn;
private String title;
private Integer author;
private Double price;

private String authorName;
}
