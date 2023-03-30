package com.example.library.dtoMapper;


import com.example.library.domain.Reader;
import com.example.library.dto.ReaderDto;
import org.springframework.stereotype.Service;

@Service
public class MapToReaderDto {

    public ReaderDto mapToReaderDto(Reader reader) {
        return new ReaderDto(
                reader.getName(),
                reader.getLastName()
        );
    }
}
