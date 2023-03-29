package com.example.library.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@AllArgsConstructor
@Table
@NoArgsConstructor
public class CopyOfABook {
    @Id
    @SequenceGenerator(
            name = "copyOfABook_sequence",
            sequenceName = "copyOfABook_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "copyOfABook_sequence",
            strategy = GenerationType.SEQUENCE)
    private Long BookCopyId;
    @ManyToOne
    private Title title;
    @Enumerated(EnumType.STRING)
    private BookState state;
}
