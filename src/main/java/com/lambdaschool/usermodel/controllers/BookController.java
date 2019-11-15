package com.lambdaschool.usermodel.controllers;

import com.lambdaschool.usermodel.models.Book;
import com.lambdaschool.usermodel.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping(value = "/books", produces = {"application/json"})
    public ResponseEntity<?> listAllBooks() {
        List<Book> books = bookService.findAll();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
