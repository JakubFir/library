package com.example.library.service;

import com.example.library.domain.*;
import com.example.library.dto.TitleDto;
import com.example.library.dtoMapper.MapTitleDtoToDomain;
import com.example.library.dtoMapper.MapToTitleDto;
import com.example.library.repository.TitleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TitleServiceTest {

    private TitleService titleService;
    @Mock
    private TitleRepository titleRepository;
    private final MapTitleDtoToDomain mapTitleDtoToDomain = new MapTitleDtoToDomain();
    private final MapToTitleDto mapToTitleDto = new MapToTitleDto();

    private Title title;
    private List<Title> titleList;


    @BeforeEach
    void setUp() {
        titleService = new TitleService(titleRepository, mapTitleDtoToDomain, mapToTitleDto);
        titleList = new ArrayList<>();
         Long id = 1L;
        title = new Title(id, "asd", "asd", null, new Date());
    }

    @Test
    void addTitle() {
        //given
        TitleDto titleDto = mapToTitleDto.mapToTitleDto(title);

        //when
        titleService.addTitle(titleDto);
        ArgumentCaptor<Title> titleArgumentCaptor = ArgumentCaptor.forClass(Title.class);
        verify(titleRepository).save(titleArgumentCaptor.capture());
        Title title1 = titleArgumentCaptor.getValue();

        //then
        assertThat(title1.getTitle()).isEqualTo(title.getTitle());
        assertThat(title1.getAuthor()).isEqualTo(title.getAuthor());

    }

    @Test
    void getAllTitles() {
        //given
        titleList.add(title);
        when(titleRepository.findAll()).thenReturn(titleList);

        //when
        List<TitleDto> result = titleService.getAllTitles();

        //then
        assertEquals(1, result.size());
    }
}