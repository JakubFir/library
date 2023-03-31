package com.example.library.service;

import com.example.library.domain.*;
import com.example.library.dto.BookedBookDto;
import com.example.library.dto.CopyOfABookDto;
import com.example.library.dtoMapper.MapToBookedBookDto;
import com.example.library.dtoMapper.MapToCopyOfBookDto;
import com.example.library.repository.BookedBooksRepository;
import com.example.library.repository.CopiesOfBookRepository;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class BookedBooksServiceTest {

    private BookedBooksService bookedBooksService;
    private final MapToCopyOfBookDto mapToCopyOfBookDto = new MapToCopyOfBookDto();
    private final MapToBookedBookDto mapToBookedBookDto = new MapToBookedBookDto(mapToCopyOfBookDto);
    @Mock
    private BookedBooksRepository bookedBooksRepository;
    @Mock
    private CopiesOfBookRepository copiesOfBookRepository;

    @Mock
    private ReaderRepository readerRepository;
    private Title title;
    private BookedBook bookedBook;
    private CopyOfABook copyOfABook;

    private Reader reader;

    private  List<BookedBook> bookedBooks;
    private Long id;

    @BeforeEach
    void setUp() {
        bookedBooksService = new BookedBooksService(bookedBooksRepository, readerRepository, copiesOfBookRepository, mapToBookedBookDto);
        id = 1L;
        reader = new Reader(id, "Jakub", "jakub", new Date());
        title = new Title(id, "asd", "asd", null, new Date());
        copyOfABook = new CopyOfABook(2L, title, BookState.AVAILABLE);
        bookedBook = new BookedBook(id, copyOfABook, reader, new Date(), new Date());
        bookedBooks = new ArrayList<>();
    }


    @Test
    void findAllBookedBooks() {
        //given
        bookedBooks.add(bookedBook);
        when(bookedBooksRepository.findAll()).thenReturn(bookedBooks);

        //when
        List<BookedBookDto> result = bookedBooksService.findAllBookedBooks();

        //then
        assertEquals(1, result.size());

    }


    @Test
    void borrowBook() {
        //given
        List<CopyOfABook> copyOfABookList = new ArrayList<>();
        copyOfABookList.add(copyOfABook);
        when(copiesOfBookRepository.findAll()).thenReturn(copyOfABookList);
        when(readerRepository.findById(id)).thenReturn(Optional.ofNullable(reader));

        //when
        bookedBooksService.borrowBook(id, id);
        ArgumentCaptor<BookedBook> bookedBookArgumentCaptor = ArgumentCaptor.forClass(BookedBook.class);
        verify(bookedBooksRepository).save(bookedBookArgumentCaptor.capture());
        BookedBook capturedBookedBook = bookedBookArgumentCaptor.getValue();

        //then
        assertThat(copyOfABook.getState()).isEqualTo(BookState.RENTED);
        assertThat(capturedBookedBook.getCopyOfABook()).isEqualTo(copyOfABook);
        assertThat(capturedBookedBook.getCopyOfABook().getTitle()).isEqualTo(title);
    }

    @Test
    void returnBook() {
        //given
        when(bookedBooksRepository.findById(bookedBook.getBookedBookId())).thenReturn(Optional.of(bookedBook));
        when(copiesOfBookRepository.findById(copyOfABook.getBookCopyId())).thenReturn(Optional.of(copyOfABook));

        //when
        bookedBooksService.returnBook(bookedBook.getBookedBookId(), BookState.AVAILABLE);

        //then
        assertThat(copyOfABook.getState()).isEqualTo(BookState.AVAILABLE);
        verify(bookedBooksRepository).deleteById(id);
    }

    @Test
    void borrowThisBook() {
        //given
        CopyOfABookDto copyOfABookDto = mapToCopyOfBookDto.mapToDto(copyOfABook);
        when(copiesOfBookRepository.findById(copyOfABook.getBookCopyId())).thenReturn(Optional.of(copyOfABook));
        when(readerRepository.findById(id)).thenReturn(Optional.of(reader));

        //when
        bookedBooksService.borrowThisBook(copyOfABookDto, id);
        ArgumentCaptor<BookedBook> bookedBookArgumentCaptor = ArgumentCaptor.forClass(BookedBook.class);
        verify(bookedBooksRepository).save(bookedBookArgumentCaptor.capture());
        BookedBook capturedBookedBook = bookedBookArgumentCaptor.getValue();

        //then
        assertThat(copyOfABook.getState()).isEqualTo(BookState.RENTED);
        assertThat(capturedBookedBook.getCopyOfABook()).isEqualTo(copyOfABook);
        assertThat(capturedBookedBook.getCopyOfABook().getTitle()).isEqualTo(title);
    }

    @Test
    void getReaderBookedBooks() {
        //given
        CopyOfABook copyOfABook2 = new CopyOfABook(2L, title, BookState.AVAILABLE);
        BookedBook bookedBook2 = new BookedBook(2L, copyOfABook2, reader, new Date(), new Date());
        List<BookedBook> bookedBooks = new ArrayList<>();
        bookedBooks.add(bookedBook);
        bookedBooks.add(bookedBook2);
        when(bookedBooksRepository.findAll()).thenReturn(bookedBooks);

        //when
        List<BookedBookDto> result = bookedBooksService.getReaderBookedBooks(id);

        //then
        assertThat(result.get(0).getReader()).isEqualTo(bookedBook.getReader());
        assertThat(result.get(1).getReader()).isEqualTo(bookedBook.getReader());
        assertThat(result.get(0).getCopyOfABook().getCopyOfABookId()).isEqualTo(bookedBook.getCopyOfABook().getBookCopyId());
        assertThat(result.get(1).getCopyOfABook().getCopyOfABookId()).isEqualTo(bookedBook2.getCopyOfABook().getBookCopyId());
    }


}