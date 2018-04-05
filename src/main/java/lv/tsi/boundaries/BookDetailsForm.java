package lv.tsi.boundaries;

import lv.tsi.entities.Book;
import lv.tsi.entities.Reservation;
import lv.tsi.entities.Status;
import lv.tsi.entities.User;
import org.apache.log4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
@Named
public class BookDetailsForm {
    private static final Logger logger = Logger.getLogger(BookDetailsForm.class);
    @PersistenceContext
    private EntityManager entityManager;
    @Inject
    private CurrentUser currentUser;

    private Long bookId;
    private Book book;


    @Transactional
    public void findBook() {
        book = entityManager.find(Book.class,bookId);
    }

    @Transactional
    public void reserve(){
        logger.info("RESERVATION STARTED");
        User user = currentUser.getSignedInUser();

        findBook();
        logger.info("User" + user.getFullName());
        logger.info("Book "+ book.getTitle());
        List<Reservation> reservations = entityManager
                .createQuery("SELECT r from Reservation r where r.user = :user and r.book = :book and r.status <> :status")
                .setParameter("user",user)
                .setParameter("book",book)
                .setParameter("status",Status.RELEASED)
                .getResultList();


        if (!reservations.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Already reserved"));
            return;
        }


        Reservation reservation = new Reservation();
        reservation.setBook(book);
        reservation.setUser(user);
        reservation.setStatus(Status.WAIT);
        entityManager.persist(reservation);
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
