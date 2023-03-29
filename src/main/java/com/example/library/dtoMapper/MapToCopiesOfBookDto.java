package com.example.library.dtoMapper;

import com.example.library.domain.CopyOfABook;
import com.example.library.dto.CopyOfABookDto;
import org.springframework.stereotype.Service;

@Service
public class MapToCopiesOfBookDto {
    public CopyOfABookDto mapToDto(CopyOfABook copyOfABook) {
        return new CopyOfABookDto(
                copyOfABook.getTitle().getTitleId(),
                copyOfABook.getState()
        );
    }
}
