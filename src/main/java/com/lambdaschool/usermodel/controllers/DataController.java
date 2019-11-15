package com.lambdaschool.usermodel.controllers;

import com.lambdaschool.usermodel.logging.Loggable;
import com.lambdaschool.usermodel.models.Book;
import com.lambdaschool.usermodel.services.AuthorService;
import com.lambdaschool.usermodel.services.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;

@Loggable
@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    BookService bookService;

    @Autowired
    AuthorService authorService;

    private static final Logger logger = LoggerFactory.getLogger(DataController.class);

    @PutMapping(value = "/books/{id}", consumes = {"application/json"})
    public ResponseEntity<?> updateBookById(HttpServletRequest request, @RequestBody Book book, @PathVariable long id) {
        logger.info(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");
        bookService.update(book, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/books/{bookid}/authors/{authorid}", consumes = {"application/json"})
    public ResponseEntity postBookAuthorById(HttpServletRequest request, @PathVariable long bookid, @PathVariable long authorid) {
        logger.info(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");
        bookService.addBookAuthor(bookid, authorid);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/books/{id}")
    public ResponseEntity<?> deleteBook(HttpServletRequest request, @PathVariable long id) {
        logger.info(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
