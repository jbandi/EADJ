package org.books.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

import org.books.dao.BookDao;
import org.books.domain.Book;

@Stateful(name="BookManager")
public class BookManagerImpl implements BookManagerLocal{

	@EJB
	private BookDao bookDao;
	private String message = "first";
	
	public List<Book> getBooks() throws Exception {
		return bookDao.getBooks();
	}

	public void setMessage(String message){
		this.message = message;
	}
	
	public String getMessage(){
		return message;
	}
}
