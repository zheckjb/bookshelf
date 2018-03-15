package lv.tsi.boundaries;

import lv.tsi.entities.Book;
import org.apache.log4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RequestScoped
@Named
public class BookDetailsForm {
    private static final Logger logger = Logger.getLogger(BookDetailsForm.class);
    @PersistenceContext
    private EntityManager entityManager;

    private Long bookId;
    private Book book;


    @Transactional
    public void findBook() {
        book = entityManager.find(Book.class,bookId);
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
