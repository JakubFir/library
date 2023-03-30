package com.example.library.dtoMapper;

import com.example.library.domain.Reader;
import com.example.library.dto.ReaderDto;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MapReaderDtoToDomain {

    public Reader mapReaderDtoToDomain(ReaderDto readerDto) {
        return new Reader(
                readerDto.getName(),
                readerDto.getLastName(),
                new Date()
        );
    }
}
