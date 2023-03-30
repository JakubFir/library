package com.example.library.dtoMapper;

import com.example.library.domain.CopyOfABook;
import com.example.library.dto.CopyOfABookDto;
import org.springframework.stereotype.Service;

@Service
public class MapToCopyOfBookDto {
    public CopyOfABookDto mapToDto(CopyOfABook copyOfABook) {
        return new CopyOfABookDto(
                copyOfABook.getTitle().getTitleId(),
                copyOfABook.getBookCopyId(),
                copyOfABook.getState()
        );
    }
}
