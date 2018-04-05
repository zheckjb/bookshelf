package lv.tsi.entities;

import javax.persistence.*;

@Entity
public class Reservation {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Book book;
    @ManyToOne
    private User user;
    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
