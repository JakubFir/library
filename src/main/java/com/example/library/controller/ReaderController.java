package com.example.library.controller;

import com.example.library.domain.Reader;
import com.example.library.service.ReaderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/readers")
public class ReaderController {

    private final ReaderService readerService;

    @PostMapping
    public void addReader(@RequestBody Reader reader){
        readerService.addReader(reader);
    }
    @GetMapping
    public List<Reader> getReaders(){
        return  readerService.getALlReaders();
    }
}
