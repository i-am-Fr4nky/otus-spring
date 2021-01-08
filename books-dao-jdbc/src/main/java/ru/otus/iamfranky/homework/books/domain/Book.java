package ru.otus.iamfranky.homework.books.domain;

import lombok.Data;

import java.util.Objects;
import java.util.Set;

@Data
public class Book {

    private int id;
    private String name;
    private String desc;

    private Author author;
    private Set<Genre> genres;

    public Book(int id, String name, String desc, Author author) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.author = author;
    }

    public Book(int id, String name, String desc, Author author, Set<Genre> genres) {
        this(id, name, desc, author);
        this.genres = genres;
    }

    public static Book forParams(String name, String desc, Author author) {
        return new Book(0, name, desc, author);
    }

    public static Book forParams(String name, String desc, Author author, Set<Genre> genres) {
        return new Book(0, name, desc, author, genres);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
