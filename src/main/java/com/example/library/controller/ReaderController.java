package com.example.library.controller;


import com.example.library.dto.ReaderDto;
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
    public void addReader(@RequestBody ReaderDto readerDto){
        readerService.addReader(readerDto);
    }
    @GetMapping
    public List<ReaderDto> getReaders(){
        return  readerService.getALlReaders();
    }

    @GetMapping(path = "{id}")
    public ReaderDto getReader(@PathVariable("id") Long readerId){
        return readerService.getReader(readerId);
    }
}
