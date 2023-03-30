package com.example.library.dtoMapper;

import com.example.library.domain.CopyOfABook;
import com.example.library.domain.Title;
import com.example.library.dto.CopyOfABookDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MapCopyOfABookDtoToDomain {



    public CopyOfABook mapCopyOfABookDtoToDomain(CopyOfABookDto copyOfABookDto,Title title){
        return new CopyOfABook(
                title,
                copyOfABookDto.getState()
        );
    }
}
