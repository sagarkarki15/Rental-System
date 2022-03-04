package com.sagarthyme.brs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50 , unique = true)
    private String name;

    private String numberOfPages;

    private String isbn;

    private String rating;

    private String stockCount;

    private String publishedDate;

    //photo
    private String filePath;

    @ManyToMany
    @JoinTable(name = "tbl_book_author", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authorList;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_book_category_id"))
    private Category category;
}
