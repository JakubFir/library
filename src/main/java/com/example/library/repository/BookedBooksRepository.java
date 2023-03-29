package com.example.library.repository;

import com.example.library.domain.BookedBook;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookedBooksRepository extends CrudRepository<BookedBook, Long> {

    List<BookedBook> findAll();
}
