package com.example.library.service;

import com.example.library.domain.Title;
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

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TitleServiceTest {

    private TitleService titleService;
    @Mock
    private TitleRepository titleRepository;
    private final MapTitleDtoToDomain mapTitleDtoToDomain = new MapTitleDtoToDomain();
    private final MapToTitleDto mapToTitleDto = new MapToTitleDto();

    @BeforeEach
    void setUp() {
        titleService = new TitleService(titleRepository, mapTitleDtoToDomain, mapToTitleDto);
    }

    @Test
    void addTitle() {
        //given
        Long id = 1L;
        Title title = new Title(id, "asd", "asd", null, new Date());
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
    }
}