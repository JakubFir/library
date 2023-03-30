package com.example.library.service;

import com.example.library.domain.Reader;
import com.example.library.dto.ReaderDto;
import com.example.library.dtoMapper.MapReaderDtoToDomain;
import com.example.library.dtoMapper.MapToReaderDto;
import com.example.library.repository.ReaderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReaderService {

    private final ReaderRepository readerRepository;

    private final MapReaderDtoToDomain mapReaderDtoToDomain;

    private final MapToReaderDto mapToReaderDto;

    public void addReader(ReaderDto readerDto) {
        Reader reader = mapReaderDtoToDomain.mapReaderDtoToDomain(readerDto);
        readerRepository.save(reader);
    }

    public List<ReaderDto> getALlReaders() {
        return readerRepository.findAll().stream().map(mapToReaderDto::mapToReaderDto).collect(Collectors.toList());
    }

    public ReaderDto getReader(Long readerId) {
        Reader reader = readerRepository.findById(readerId).orElseThrow();
        return mapToReaderDto.mapToReaderDto(reader);
    }
}
