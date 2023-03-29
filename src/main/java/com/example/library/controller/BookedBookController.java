package com.example.library.controller;


import com.example.library.domain.BookState;

import com.example.library.dto.BookedBookDto;

import com.example.library.service.BookedBooksService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/bookedbooks")
@AllArgsConstructor
public class BookedBookController {

    private final BookedBooksService bookedBooksService;

    @PostMapping(value = "{titleId}/{readerId}")
    public void borrowBook(@PathVariable("titleId") Long titleId, @PathVariable("readerId") Long readerId) {
        bookedBooksService.borrowBook(titleId, readerId);
    }

    @GetMapping
    public List<BookedBookDto> getBookedBooks() {
        return bookedBooksService.findAllBookedBooks();
    }

    @DeleteMapping(value = "{bookedBookId}")
    public void returnBook(@PathVariable("bookedBookId") Long bookedId, @RequestBody BookState state) {
        bookedBooksService.returnBook(bookedId, state);

    }


}
