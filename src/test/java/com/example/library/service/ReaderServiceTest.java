package com.example.library.service;

import com.example.library.domain.*;
import com.example.library.dto.ReaderDto;
import com.example.library.dtoMapper.MapReaderDtoToDomain;
import com.example.library.dtoMapper.MapToReaderDto;
import com.example.library.repository.ReaderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReaderServiceTest {

    private ReaderService readerService;
    @Mock
    private ReaderRepository readerRepository;
    private final MapReaderDtoToDomain mapReaderDtoToDomain = new MapReaderDtoToDomain();
    private final MapToReaderDto mapToReaderDto = new MapToReaderDto();
    private Reader reader;

    private List<Reader> readerList;
    private Long id;

    @BeforeEach
    void setUp() {
        readerService = new ReaderService(readerRepository,mapReaderDtoToDomain,mapToReaderDto);
        id = 1L;
        reader = new Reader(id, "Jakub", "jakub", new Date());
        readerList= new ArrayList<>();

    }

    @Test
    void addReader() {
        //given
        ReaderDto readerDto = mapToReaderDto.mapToReaderDto(reader);

        //when
        readerService.addReader(readerDto);

        //then
        ArgumentCaptor<Reader> readerArgumentCaptor = ArgumentCaptor.forClass(Reader.class);
        verify(readerRepository).save(readerArgumentCaptor.capture());
        Reader reader1 = readerArgumentCaptor.getValue();
        assertThat(reader1.getName()).isEqualTo(reader.getName());
        assertThat(reader1.getLastName()).isEqualTo(reader.getLastName());

    }

    @Test
    void getALlReaders() {
        //given
        readerList.add(reader);
        when(readerRepository.findAll()).thenReturn(readerList);

        //when
        List<ReaderDto> result = readerService.getALlReaders();

        //then
        assertEquals(1,result.size());
    }

    @Test
    void getReader() {
        //given
        when(readerRepository.findById(id)).thenReturn(Optional.of(reader));

        //when
        ReaderDto result = readerService.getReader(id);

        //then
        assertThat(result.getName()).isEqualTo(reader.getName());
        assertThat(result.getLastName()).isEqualTo(reader.getLastName());
    }
}