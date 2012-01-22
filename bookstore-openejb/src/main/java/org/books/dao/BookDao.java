package org.books.dao;

import org.books.domain.Book;

import java.util.List;

public interface BookDao {
    void addBook(Book movie) throws Exception ;

    void deleteBook(Book movie) throws Exception ;

    List<Book> getBooks() throws Exception ;
}
