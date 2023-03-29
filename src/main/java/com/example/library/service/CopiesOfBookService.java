package com.example.library.service;

import com.example.library.domain.BookState;
import com.example.library.domain.CopyOfABook;
import com.example.library.domain.Title;
import com.example.library.dto.CopyOfABookDto;
import com.example.library.dtoMapper.MapToCopiesOfBookDto;
import com.example.library.repository.CopiesOfBookRepository;
import com.example.library.repository.TitleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CopiesOfBookService {

    private final CopiesOfBookRepository copiesOfBookRepository;
    private final MapToCopiesOfBookDto mapToCopiesDto;
    private final TitleRepository titleRepository;


    public List<CopyOfABookDto> findAllCopies() {
        List<CopyOfABook> copyOfABookList = copiesOfBookRepository.findAll();
        return copyOfABookList.stream().map(mapToCopiesDto::mapToDto).collect(Collectors.toList());
    }

    public void addCopiesOfBook(CopyOfABook copyOfABook, Long titleId) {
        Title title = titleRepository.findById(titleId).orElseThrow();
        title.getCopyOfABookList().add(copyOfABook);
        copyOfABook.setTitle(title);
        copiesOfBookRepository.save(copyOfABook);
    }

    public List<CopyOfABookDto> getAvailableCopiesOfBook(Long titleId) {
        List<CopyOfABook> copyOfABookList = copiesOfBookRepository.findAll();

        return copyOfABookList.stream()
                .filter(copiesOfBook -> copiesOfBook.getTitle().getTitleId().equals(titleId))
                .collect(Collectors.toList())
                .stream()
                .filter(copiesOfBook -> copiesOfBook.getState().equals(BookState.AVAILABLE))
                .map(mapToCopiesDto::mapToDto)
                .collect(Collectors.toList());
    }


    public void changeStateOfBook(BookState bookState, Long titleId) {
        CopyOfABook copyOfBook = copiesOfBookRepository.findById(titleId).orElseThrow();
        copyOfBook.setState(bookState);
        copiesOfBookRepository.save(copyOfBook);

    }
}
