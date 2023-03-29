package com.example.library.service;

import com.example.library.domain.Reader;
import com.example.library.repository.ReaderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReaderService {

    private final ReaderRepository readerRepository;

    public void addReader(Reader reader) {
        readerRepository.save(reader);
    }

    public List<Reader> getALlReaders() {
        return readerRepository.findAll();
    }
}
