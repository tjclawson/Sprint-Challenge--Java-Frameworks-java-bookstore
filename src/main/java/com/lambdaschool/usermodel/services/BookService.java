package com.lambdaschool.usermodel.services;

import com.lambdaschool.usermodel.models.Book;

import java.util.List;

public interface BookService {

    List<Book> findAll();

    Book update(Book book, long bookid);

    void delete(long bookid);

    void addBookAuthor(long bookid, long authorid);
}
