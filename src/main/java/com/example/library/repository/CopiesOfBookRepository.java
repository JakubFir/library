package com.example.library.repository;


import com.example.library.domain.CopyOfABook;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CopiesOfBookRepository extends CrudRepository<CopyOfABook, Long> {

    List<CopyOfABook> findAll();


}
