/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Notreal
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "aStorePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    public User findByLoginAndPassword(String login, String password) {
        List<User> users = em.createNamedQuery("User.findByEmailAndPassword", User.class)
                .setParameter("email", login)
                .setParameter("password", password)
                .getResultList();
        return users.isEmpty() ? null : users.get(0);
    }

    public User findByAuthIdAndAuthToken(String authId, String authToken) {
        List<User> users = em.createNamedQuery("User.findByEmailAndUuid", User.class)
                    .setParameter("email", authId)
                    .setParameter("uuid", authToken)
                    .getResultList();
        return users.isEmpty() ? null : users.get(0);
    }

}
