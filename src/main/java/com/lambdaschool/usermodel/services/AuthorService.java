package com.lambdaschool.usermodel.services;

import com.lambdaschool.usermodel.models.Author;

import java.util.List;

public interface AuthorService {

    List<Author> findAll();
}
