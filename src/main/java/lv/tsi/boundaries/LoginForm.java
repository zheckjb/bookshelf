package lv.tsi.boundaries;

import lv.tsi.entities.User;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@RequestScoped
@Named
public class LoginForm {
    @PersistenceContext
    private EntityManager em;
    @Inject
    private CurrentUser currentUser;

    private String email;
    private String password;

    @Transactional
    public String login() {
        TypedQuery<User> query = em.createQuery("select u from User u where u.email = :paremail",User.class);
        query.setParameter("paremail",email);
//        User mu = query.getSingleResult();
//        Exceptions can be rised: NoResultException,NonUniqueResultException

//        List<User> users = query.getResultList();
        try {
            User mu = query.getSingleResult();
            if (Objects.equals(mu.getPassword(),password)) {
                currentUser.setSignedInUser(mu);
                currentUser.setUserId(mu.getId());
                return "/index.xhtml";
            } else {
                FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Incorrect password"));
            }

        } catch (NoResultException e) {
            FacesContext.getCurrentInstance().addMessage("login:email",new FacesMessage("Email is not register"));
        }

        return null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
