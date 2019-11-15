package com.lambdaschool.usermodel.repository;

import com.lambdaschool.usermodel.models.Book;
import com.lambdaschool.usermodel.view.JustTheCount;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface BookRepository extends CrudRepository<Book, Long> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO wrote(authorid, bookid, created_by, created_date, last_modified_by, last_modified_date) VALUES (:authorid, :bookid, :username, CURRENT_TIMESTAMP, :username, CURRENT_TIMESTAMP)",
            nativeQuery = true)
    void insertWrote(String username, long authorid, long bookid);

    @Query(value = "SELECT COUNT(*) as count FROM wrote WHERE bookid = :bookid AND authorid = :authorid",
            nativeQuery = true)
    JustTheCount checkBookAuthorCombo(long bookid, long authorid);
}
