package com.example.library.service;

import com.example.library.domain.*;
import com.example.library.dtoMapper.MapToBookedBookDto;
import com.example.library.dtoMapper.MapToCopiesOfBookDto;
import com.example.library.repository.BookedBooksRepository;
import com.example.library.repository.CopiesOfBookRepository;
import com.example.library.repository.ReaderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class BookedBooksServiceTest {

    private  BookedBooksService bookedBooksService;
    private final MapToCopiesOfBookDto mapToCopiesOfBookDto = new MapToCopiesOfBookDto();
    private final MapToBookedBookDto mapToBookedBookDto = new MapToBookedBookDto(mapToCopiesOfBookDto);
    @Mock
    private BookedBooksRepository bookedBooksRepository;
    @Mock
    private CopiesOfBookRepository copiesOfBookRepository;

    @Mock
    private  ReaderRepository readerRepository;

    @BeforeEach
    void setUp() {
        bookedBooksService = new BookedBooksService(bookedBooksRepository,readerRepository,copiesOfBookRepository,mapToBookedBookDto);
    }

    @Test
    void borrowBook() {
        //given
        Long id =1L;
        Reader reader = new Reader(id,"Jakub","jakub",new Date());
        when(readerRepository.findById(id)).thenReturn(Optional.of(reader));
        Title title = new Title(id,"asd","asd",null,new Date());

        CopyOfABook copyOfABook = new CopyOfABook(2L,title, BookState.AVAILABLE);
        List<CopyOfABook> copyOfABookList = new ArrayList<>();
        copyOfABookList.add(copyOfABook);

        when(copiesOfBookRepository.findAll()).thenReturn(copyOfABookList);

        //when
        bookedBooksService.borrowBook(id,id);
        //then
        assertThat(copyOfABook.getState()).isEqualTo(BookState.RENTED);


    }

    @Test
    void returnBook() {
    }

    @Test
    void findAllBookedBooks() {
    }
}