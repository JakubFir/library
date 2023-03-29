package com.example.library.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode
@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@Table
@NoArgsConstructor
public class BookedBook {
    @Id
    @SequenceGenerator(
            name = "bookedBook_sequence",
            sequenceName = "bookedBook_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "bookedBook_sequence",
            strategy = GenerationType.SEQUENCE)
    private Long BookedBookId;
    @OneToOne
    private CopyOfABook copyOfABook;
    @OneToOne
    private Reader reader;
    private Date rentalDate;
    private Date returnDate;


}
