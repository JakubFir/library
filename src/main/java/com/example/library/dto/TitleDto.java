package com.example.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
public class TitleDto {
    private String title;
    private String author;
    private Date publishedAt = new Date();

}
