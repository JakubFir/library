package com.example.library.controller;

import com.example.library.domain.BookState;
import com.example.library.dto.CopyOfABookDto;
import com.example.library.service.CopyOfBookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/copiesofbook")
public class CopyOfBookController {

    private final CopyOfBookService copyOfBookService;

    @PostMapping
    public void addCopyOfABook(@RequestBody CopyOfABookDto copyOfABookDto){
        copyOfBookService.addCopiesOfBook(copyOfABookDto);

    }
    @PutMapping(path = "{id}")
    public void changeStateOfBook(@RequestBody BookState bookState,@PathVariable("id") Long copyOfBookId){
       copyOfBookService.changeStateOfBook(bookState,copyOfBookId);
    }

    @GetMapping(path = "{id}")
    public List<CopyOfABookDto> getAllCopiesOfBook(@PathVariable("id") Long titleId){
        return copyOfBookService.getAvailableCopiesOfBook(titleId);
    }

    @GetMapping
    public List<CopyOfABookDto> getAllCopiesOfBook(){
        return copyOfBookService.findAllCopies();
    }

}
