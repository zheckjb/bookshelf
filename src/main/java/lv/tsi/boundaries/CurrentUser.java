package lv.tsi.boundaries;

import lv.tsi.entities.User;
import org.apache.log4j.Logger;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;

@SessionScoped
@Named
public class CurrentUser implements Serializable {
    @PersistenceContext
    private EntityManager em;
    private Long userId;
    private User signedInUser;

    @Transactional
    public void signIn() {
        userId = 1L;
        signedInUser = em.find(User.class, userId);
    }

    public void signOut() {
        userId = null;
        signedInUser = null;
    }

    public boolean isSignedIn() {
        return userId != null;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public User getSignedInUser() {
        return signedInUser;
    }

    public void setSignedInUser(User signedInUser) {
        this.signedInUser = signedInUser;
    }
}
