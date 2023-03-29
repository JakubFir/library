package com.example.library.service;

import com.example.library.domain.Title;
import com.example.library.dto.TitleDto;
import com.example.library.dtoMapper.MapToTitleDto;
import com.example.library.repository.TitleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TitleService {

    private final TitleRepository titleRepository;

    private final MapToTitleDto mapToTitleDto;
    public void addTitle(Title title) {
        titleRepository.save(title);
    }

    public List<TitleDto> getAllTitles() {
        List<Title> allTitles = titleRepository.findAll();
        return allTitles.stream().map(mapToTitleDto::mapToTitleDto).collect(Collectors.toList());
    }
}
