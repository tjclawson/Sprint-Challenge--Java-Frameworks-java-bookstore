package com.lambdaschool.usermodel.services;

import com.lambdaschool.usermodel.models.Book;
import com.lambdaschool.usermodel.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("bookService")
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        List<Book> list = new ArrayList<>();
        bookRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Book update(Book book) {
        Book updateBook = new Book();

        if (book.getBooktitle() != null) {
            updateBook.setBooktitle(book.getBooktitle());
        }

        if (book.getCopy() != 0) {
            updateBook.setCopy(book.getCopy());
        }

        if (book.getISBN() != null) {
            updateBook.setISBN(book.getISBN());
        }

        return bookRepository.save(updateBook);
    }

    @Override
    public void delete(long bookid, Book book) {
        bookRepository.delete(book);
    }
}
