package com.lambdaschool.usermodel.services;

import com.lambdaschool.usermodel.models.Book;

import java.util.List;

public interface BookService {

    List<Book> findAll();

    Book update(Book book);

    void delete(long bookid, Book book);
}
