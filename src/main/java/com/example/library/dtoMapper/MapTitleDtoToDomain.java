package com.example.library.dtoMapper;

import com.example.library.domain.Title;
import com.example.library.dto.TitleDto;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MapTitleDtoToDomain {

    public Title mapTitleDtoTODomain(TitleDto titleDto){
        return new Title(
                titleDto.getTitle(),
                titleDto.getAuthor(),
                new Date()
        );
    }
}
