package com.lambdaschool.usermodel.services;

import com.lambdaschool.usermodel.exceptions.ResourceFoundException;
import com.lambdaschool.usermodel.exceptions.ResourceNotFoundException;
import com.lambdaschool.usermodel.models.Book;
import com.lambdaschool.usermodel.repository.AuthorRepository;
import com.lambdaschool.usermodel.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("bookService")
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    UserAuditing userAuditing;

    @Override
    public List<Book> findAll() {
        List<Book> list = new ArrayList<>();
        bookRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Book update(Book book, long bookid) {
        Book updateBook = bookRepository.findById(bookid).orElseThrow(() -> new ResourceNotFoundException("Book with id " + bookid + " not found"));

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
    public void delete(long bookid) {
        Book deleteBook = bookRepository.findById(bookid).orElseThrow(() -> new ResourceNotFoundException("Book with id " + bookid + " not found"));
        bookRepository.delete(deleteBook);
    }

    @Override
    public void addBookAuthor(long bookid, long authorid) {
        bookRepository.findById(bookid).orElseThrow(() -> new ResourceNotFoundException("Book with id " + bookid + " not found"));
        authorRepository.findById(authorid).orElseThrow(() -> new ResourceNotFoundException("Author with id " + authorid + " not found"));

        if (bookRepository.checkBookAuthorCombo(bookid, authorid).getCount() <= 0) {
            bookRepository.insertWrote(userAuditing.getCurrentAuditor().get(), authorid, bookid);
        } else {
            throw new ResourceFoundException("Book Author combo already exists");
        }
    }
}
