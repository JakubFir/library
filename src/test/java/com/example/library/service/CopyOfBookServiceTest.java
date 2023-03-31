package com.example.library.service;

import com.example.library.domain.*;
import com.example.library.dto.CopyOfABookDto;
import com.example.library.dtoMapper.MapCopyOfABookDtoToDomain;
import com.example.library.dtoMapper.MapToCopyOfBookDto;
import com.example.library.repository.CopiesOfBookRepository;
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
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CopyOfBookServiceTest {

    private CopyOfBookService copyOfBookService;
    private final MapToCopyOfBookDto mapToCopyOfBookDto = new MapToCopyOfBookDto();
    @Mock
    private TitleRepository titleRepository;
    private final MapCopyOfABookDtoToDomain mapCopyOfABookDtoToDomain = new MapCopyOfABookDtoToDomain();
    @Mock
    private CopiesOfBookRepository copiesOfBookRepository;
    private Title title;
    private CopyOfABook copyOfABook;
    private List<CopyOfABook> list;
    private Long id;


    @BeforeEach
    void setUp() {
        copyOfBookService = new CopyOfBookService(
                copiesOfBookRepository,
                mapToCopyOfBookDto,
                mapCopyOfABookDtoToDomain,
                titleRepository);

        list = new ArrayList<>();
        id = 1L;
        title = new Title(id, "asd", "asd", list, new Date());
        copyOfABook = new CopyOfABook(2L, title, BookState.AVAILABLE);

    }

    @Test
    void findAllCopies() {
        //given
        list.add(copyOfABook);
        when(copiesOfBookRepository.findAll()).thenReturn(list);

        //when
        List<CopyOfABookDto> result = copyOfBookService.findAllCopies();

        //then
        assertEquals(1, result.size());

    }

    @Test
    void addCopiesOfBook() {
        //given
        when(titleRepository.findById(id)).thenReturn(Optional.of(title));
        CopyOfABookDto copyOfABookDto = new CopyOfABookDto(id, 1L, BookState.AVAILABLE);

        //when
        copyOfBookService.addCopiesOfBook(copyOfABookDto);

        //then
        ArgumentCaptor<CopyOfABook> copyOfABookArgumentCaptor = ArgumentCaptor.forClass(CopyOfABook.class);
        verify(copiesOfBookRepository).save(copyOfABookArgumentCaptor.capture());
        CopyOfABook copyOfABook = copyOfABookArgumentCaptor.getValue();
        assertThat(copyOfABook.getTitle()).isEqualTo(title);

    }

    @Test
    void getAvailableCopiesOfBook() {
        //given
        CopyOfABook copyOfABook1 = new CopyOfABook(1L, title, BookState.AVAILABLE);
        CopyOfABook copyOfABook2 = new CopyOfABook(2L, title, BookState.DESTROYED);
        CopyOfABook copyOfABook3 = new CopyOfABook(3L, title, BookState.DESTROYED);
        list.add(copyOfABook1);
        list.add(copyOfABook2);
        list.add(copyOfABook3);
        when(copiesOfBookRepository.findAll()).thenReturn(list);

        //when
        List<CopyOfABookDto> result = copyOfBookService.getAvailableCopiesOfBook(id);
        int resultSize = result.size();

        //then
        assertEquals(1, resultSize);

    }

    @Test
    void changeStateOfBook() {
        //given
        CopyOfABook copyOfABook2 = new CopyOfABook(2L, title, BookState.DESTROYED);
        when(copiesOfBookRepository.findById(id)).thenReturn(Optional.of(copyOfABook2));

        //when
        copyOfBookService.changeStateOfBook(BookState.LOST, id);

        //then
        assertThat(copyOfABook2.getState()).isEqualTo(BookState.LOST);
    }

}

