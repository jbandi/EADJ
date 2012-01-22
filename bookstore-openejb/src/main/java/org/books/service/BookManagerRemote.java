package org.books.service;

import java.util.List;

import javax.ejb.Remote;

import org.books.domain.Book;

@Remote
public interface BookManagerRemote {

    List<Book> getBooks() throws Exception ;
}
