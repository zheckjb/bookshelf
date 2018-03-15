package lv.tsi.boundaries;

import lv.tsi.entities.User;
import org.apache.log4j.Logger;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@SessionScoped
@Named
public class CurrentUser implements Serializable {
    private static final Logger logger = Logger.getLogger(CurrentUser.class);
    private Long userId;
    private User signedInUser;

    public boolean isSignedIn(){
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

    public void signIn() {
        userId = 1L;
        signedInUser = new User();
        signedInUser.setId(1L);
        signedInUser.setFullName("Vasya Pupkin");
        logger.info("User: "+signedInUser.getFullName());
    }

    public void signOut() {
        userId = null;
        signedInUser = null;
    }
}
