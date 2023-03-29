package com.example.library.dto;

import com.example.library.domain.BookState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CopyOfABookDto {
    private Long titleId;
    private BookState state;

}
