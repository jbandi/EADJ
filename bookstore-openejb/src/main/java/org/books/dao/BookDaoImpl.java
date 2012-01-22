package org.books.dao;

import org.books.domain.Book;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.PersistenceContextType;
import java.util.List;

@Stateful(name = "BookDao")
public class BookDaoImpl implements BookDao {

    @PersistenceContext(unitName = "bookstore", type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    public void addBook(Book book) throws Exception {
        entityManager.persist(book);
    }

    public void deleteBook(Book book) throws Exception {
        entityManager.remove(book);
    }

    public List<Book> getBooks() throws Exception {
        Query query = entityManager.createQuery("SELECT m from Book as m");
        return query.getResultList();
    }

}
