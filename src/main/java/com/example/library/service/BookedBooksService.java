package com.example.library.service;


import com.example.library.domain.*;
import com.example.library.dto.BookedBookDto;
import com.example.library.dto.CopyOfABookDto;

import com.example.library.dtoMapper.MapToBookedBookDto;
import com.example.library.exceptions.BookNotAvailableException;
import com.example.library.repository.BookedBooksRepository;
import com.example.library.repository.CopiesOfBookRepository;
import com.example.library.repository.ReaderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookedBooksService {

    private final BookedBooksRepository bookedBooksRepository;
    private final ReaderRepository readerRepository;

    private final CopiesOfBookRepository copiesOfBookRepository;

    private final MapToBookedBookDto mapToBookedBookDto;

    public void borrowBook(Long titleId, Long readerId) {

        List<CopyOfABook> availableCopies = availableCopies(titleId);

        if (availableCopies.size() == 0) {
            throw new BookNotAvailableException("book not available");
        }
        Reader reader = readerRepository.findById(readerId).orElseThrow();

        BookedBook bookedBook = new BookedBook();
        CopyOfABook copyOfBook = availableCopies.get(0);

        bookedBook.setCopyOfABook(availableCopies.get(0));
        bookedBook.setReader(reader);
        bookedBook.setRentalDate(new Date());
        bookedBook.setReturnDate(new Date(System.currentTimeMillis() + 10000 * 60 * 24));
        copyOfBook.setState(BookState.RENTED);
        bookedBooksRepository.save(bookedBook);
    }

    public void returnBook(Long bookedBookId, BookState state) {
        BookedBook bookedBook = bookedBooksRepository.findById(bookedBookId).orElseThrow();
        Long bookId = bookedBook.getCopyOfABook().getBookCopyId();
        CopyOfABook returnedBook = copiesOfBookRepository.findById(bookId).orElseThrow();
        returnedBook.setState(state);
        bookedBooksRepository.deleteById(bookedBookId);
    }

    public List<BookedBookDto> findAllBookedBooks() {
        List<BookedBook> bookedBooks = bookedBooksRepository.findAll();

        return bookedBooks.stream().map(mapToBookedBookDto::mapToBookedBookDto).collect(Collectors.toList());
    }

    private List<CopyOfABook> availableCopies(Long titleId) {
        List<CopyOfABook> copyOfABookList = copiesOfBookRepository.findAll();
        return copyOfABookList.stream()
                .filter(book -> book.getTitle().getTitleId().equals(titleId))
                .filter(book -> book.getState().equals(BookState.AVAILABLE))
                .collect(Collectors.toList());
    }

    public void borrowThisBook(CopyOfABookDto copyOfABookDto, Long readerId) {
        BookedBook bookedBook = new BookedBook();
        Reader reader = readerRepository.findById(readerId).orElseThrow();
        CopyOfABook copyOfABook = copiesOfBookRepository.findById(copyOfABookDto.getCopyOfABookId()).orElseThrow();

        if(!copyOfABook.getState().equals(BookState.AVAILABLE)){
            throw new BookNotAvailableException("Current book cant be rented");
        }

        bookedBook.setCopyOfABook(copyOfABook);
        bookedBook.setReader(reader);
        bookedBook.setRentalDate(new Date());
        bookedBook.setReturnDate(new Date(System.currentTimeMillis() + 10000 * 60 * 24));
        copyOfABook.setState(BookState.RENTED);

        bookedBooksRepository.save(bookedBook);
    }

    public List<BookedBookDto> getReaderBookedBooks(Long readerId) {

        return bookedBooksRepository.findAll().stream()
                .filter(bookedBook -> bookedBook.getReader().getReaderId().equals(readerId))
                .map(mapToBookedBookDto::mapToBookedBookDto).collect(Collectors.toList());

    }
}
