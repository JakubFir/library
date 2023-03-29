package com.example.library.repository;



import com.example.library.domain.Title;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TitleRepository extends CrudRepository<Title, Long> {
    List<Title> findAll();
}
