package com.example.library.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@AllArgsConstructor
@Table
@NoArgsConstructor
public class Title {
    @Id
    @SequenceGenerator(
            name = "title_sequence",
            sequenceName = "title_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "title_sequence",
            strategy = GenerationType.SEQUENCE)
    private Long titleId;
    private String title;
    private String author;
    @OneToMany(
            targetEntity = CopyOfABook.class,
            mappedBy = "title",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<CopyOfABook> copyOfABookList;
    private Date publishedAt = new Date();

    public Title(String title, String author, Date publishedAt) {
        this.title = title;
        this.author = author;
        this.publishedAt = publishedAt;
    }
}

