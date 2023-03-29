package com.example.library.dtoMapper;

import com.example.library.domain.Title;
import com.example.library.dto.TitleDto;
import org.springframework.stereotype.Service;

@Service
public class MapToTitleDto {

    public TitleDto mapToTitleDto(Title title) {
        return new TitleDto(
                title.getTitle(),
                title.getAuthor(),
                title.getPublishedAt()
        );
    }
}
