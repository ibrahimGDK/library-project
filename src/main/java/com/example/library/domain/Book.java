package com.example.library.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
public class Book {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)
    private String name;

    @Column(nullable = false, length = 17, unique = true)
    private String isbn;

    @Column
    private int pageCount;

    @ManyToOne
    @JoinColumn(name = "authorId",nullable = false)
    private Author author;

    @ManyToOne
    @JoinColumn(name = "publisherId",nullable = false)
    private Publisher publisher;

    @Column(nullable = false,length = 4)
    private int publishDate;


    private Category category;



}
