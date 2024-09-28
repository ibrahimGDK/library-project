package com.example.library.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId",nullable = false)
    private Category category;

    @Column(nullable = true)
    private String imageUrl;


    @Column(nullable = false)
    private boolean loanable = true;

    @Column(nullable = false,length = 6)
    private String shelfCode;

    @Column(nullable = false)
    private boolean active = true;

    @Column(nullable = false)
    private boolean featured = false;

    @Column(nullable = false)
    private LocalDateTime createDate = LocalDateTime.now();

    @Column(nullable = false)
    private boolean builtIn = false;




}
