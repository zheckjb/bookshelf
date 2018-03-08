package lv.tsi;

import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@Named

public class BookSearch {
    private static final Logger logger = Logger.getLogger(BookSearch.class);
    @PersistenceContext
    private EntityManager entityManager;
    @Inject
    private BookSearchForm bookSearchForm;

    public List<Book> getAllBooks() {
        Query q = entityManager.createQuery("SELECT b FROM Book b");
        return q.getResultList();

    }

    public void doSearch() {
        logger.info(bookSearchForm.getTerm());
        Query q = entityManager.createQuery("SELECT b FROM Book b WHERE b.author = :name");
        q.setParameter("name",bookSearchForm.getTerm());
        bookSearchForm.setSearchResult(q.getResultList());

    }
}
