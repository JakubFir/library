package com.example.library.dtoMapper;

import com.example.library.domain.BookedBook;
import com.example.library.dto.BookedBookDto;
import com.example.library.dto.CopyOfABookDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MapToBookedBookDto {
    private final MapToCopyOfBookDto mapToCopyOfBookDto;

    public BookedBookDto mapToBookedBookDto(BookedBook bookedBook) {

        CopyOfABookDto copyOfABookDto = mapToCopyOfBookDto.mapToDto(bookedBook.getCopyOfABook());

        return new BookedBookDto(
                copyOfABookDto,
                bookedBook.getReader(),
                bookedBook.getRentalDate(),
                bookedBook.getReturnDate()
        );
    }
}
