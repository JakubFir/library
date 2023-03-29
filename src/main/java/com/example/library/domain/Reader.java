package com.example.library.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@AllArgsConstructor
@Table
@NoArgsConstructor
public class Reader {
    @Id
    @SequenceGenerator(
            name = "reader_sequence",
            sequenceName = "reader_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "reader_sequence",
            strategy = GenerationType.SEQUENCE)
    private Long ReaderId;
    private String name;
    private String lastName;
    private Date createAt = new Date();


}
