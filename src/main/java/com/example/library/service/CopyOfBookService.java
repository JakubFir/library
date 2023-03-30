package com.example.library.service;

import com.example.library.domain.BookState;
import com.example.library.domain.CopyOfABook;
import com.example.library.domain.Title;
import com.example.library.dto.CopyOfABookDto;
import com.example.library.dtoMapper.MapCopyOfABookDtoToDomain;
import com.example.library.dtoMapper.MapToCopyOfBookDto;
import com.example.library.repository.CopiesOfBookRepository;
import com.example.library.repository.TitleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CopyOfBookService {

    private final CopiesOfBookRepository copiesOfBookRepository;
    private final MapToCopyOfBookDto mapToCopiesDto;

    private final MapCopyOfABookDtoToDomain mapCopyOfABookDtoToDomain;
    private final TitleRepository titleRepository;


    public List<CopyOfABookDto> findAllCopies() {
        List<CopyOfABook> copyOfABookList = copiesOfBookRepository.findAll();
        return copyOfABookList.stream().map(mapToCopiesDto::mapToDto).collect(Collectors.toList());
    }

    public void addCopiesOfBook(CopyOfABookDto copyOfABookDto) {
        Title title = titleRepository.findById(copyOfABookDto.getTitleId()).orElseThrow();

        CopyOfABook copyOfABook = mapCopyOfABookDtoToDomain.mapCopyOfABookDtoToDomain(copyOfABookDto,title);

        title.getCopyOfABookList().add(copyOfABook);
        copyOfABook.setTitle(title);

        copiesOfBookRepository.save(copyOfABook);
    }

    public List<CopyOfABookDto> getAvailableCopiesOfBook(Long titleId) {
        List<CopyOfABook> copyOfABookList = copiesOfBookRepository.findAll();

        return copyOfABookList.stream()
                .filter(copiesOfBook -> copiesOfBook.getTitle().getTitleId().equals(titleId))
                .filter(copiesOfBook -> copiesOfBook.getState().equals(BookState.AVAILABLE))
                .map(mapToCopiesDto::mapToDto)
                .collect(Collectors.toList());
    }


    public void changeStateOfBook(BookState bookState, Long copyOfBookId) {
        CopyOfABook copyOfBook = copiesOfBookRepository.findById(copyOfBookId).orElseThrow();
        copyOfBook.setState(bookState);
        copiesOfBookRepository.save(copyOfBook);

    }
}
