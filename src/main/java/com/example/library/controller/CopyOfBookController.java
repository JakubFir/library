package com.example.library.controller;

import com.example.library.domain.BookState;
import com.example.library.domain.CopyOfABook;
import com.example.library.dto.CopyOfABookDto;
import com.example.library.service.CopiesOfBookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/copiesofbook")
public class CopyOfBookController {

    private final CopiesOfBookService copiesOfBookService;

    @PostMapping(path = "{id}")
    public void addCopyOfABook(@RequestBody CopyOfABook copyOfABook, @PathVariable("id") Long titleId){
        copiesOfBookService.addCopiesOfBook(copyOfABook,titleId);

    }
    @PutMapping(path = "{id}")
    public void changeStateOfBook(@RequestBody BookState bookState,@PathVariable("id") Long titleId){
       copiesOfBookService.changeStateOfBook(bookState,titleId);
    }

    @GetMapping(path = "{id}")
    public List<CopyOfABookDto> availableCopiesOfBook(@PathVariable("id") Long titleId){
        return copiesOfBookService.getAvailableCopiesOfBook(titleId);
    }

    @GetMapping()
    public List<CopyOfABookDto> availableCopiesOfBook(){
        return copiesOfBookService.findAllCopies();
    }

}
