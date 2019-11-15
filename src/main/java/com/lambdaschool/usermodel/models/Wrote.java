package com.lambdaschool.usermodel.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "wrote")
public class Wrote extends Auditable {

    @ManyToOne
    @JoinColumn(name = "bookid")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "authorid")
    private Author author;

    public Wrote() {
    }

    public Wrote(Book book, Author author) {
        this.book = book;
        this.author = author;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wrote)) return false;
        Wrote wrote = (Wrote) o;
        return Objects.equals(getBook(), wrote.getBook()) &&
                Objects.equals(getAuthor(), wrote.getAuthor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBook(), getAuthor());
    }
}
