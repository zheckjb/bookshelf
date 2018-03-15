package lv.tsi.boundaries;


import lv.tsi.entities.Book;
import org.apache.log4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
@Named
public class BookSearchForm {
    private static final Logger logger = Logger.getLogger(BookSearchForm.class);
    @PersistenceContext
    private EntityManager entityManager;


    private String term;
    private List<Book> searchResult;

    public List<Book> getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(List<Book> searchResult) {
        this.searchResult = searchResult;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
    @Transactional
    public List<Book> getAllBooks() {
        Query q = entityManager.createQuery("SELECT b FROM Book b");
        return q.getResultList();

    }
    @Transactional
    public void doSearch() {
        Query q = entityManager.createQuery("SELECT b FROM Book b WHERE " +
                "UPPER(b.author) LIKE :term " +
                "OR UPPER(b.isbn) LIKE :term " +
                "OR UPPER(b.title) LIKE :term " +
                "OR UPPER(b.description) LIKE :term");
        String term = "%" + getTerm().toUpperCase() + "%";
        q.setParameter("term", term);
        setSearchResult(q.getResultList());
    }
}
