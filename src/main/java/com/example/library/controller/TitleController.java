package com.example.library.controller;


import com.example.library.dto.TitleDto;
import com.example.library.service.TitleService;
import com.example.library.domain.Title;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/titles")
public class TitleController {

    private final TitleService titleService;

    @PostMapping
    public void addTitle(@RequestBody Title title) {
        titleService.addTitle(title);
    }

    @GetMapping
    public List<TitleDto> getTitles(){
        return titleService.getAllTitles();
    }




}
