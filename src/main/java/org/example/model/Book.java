package org.example.model;

import lombok.*;

import java.util.Date;


@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private int id;
    private String name;
    private Date created;
    private int authorId;
    private User author;
}
