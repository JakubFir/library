package com.example.library.dto;
import com.example.library.domain.Reader;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
public class BookedBookDto {

    private CopyOfABookDto copyOfABook;
    private Reader reader;
    private Date rentalDate;
    private Date returnDate;
}
