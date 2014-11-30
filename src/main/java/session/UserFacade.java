/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import auth.Salt;
import entity.Order;
import entity.Role;
import entity.User;
import java.util.Collection;
import java.util.Collections;
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

    public User findByEmail(String email) {
        List<User> users = em.createNamedQuery("User.findByEmail", User.class)
                .setParameter("email", email)
                .getResultList();
        return users.isEmpty() ? null : users.get(0);
    }

    public User findByLoginAndPassword(String login, String password) {
        List<User> users = em.createNamedQuery("User.findByEmailAndPassword", User.class)
                .setParameter("email", login)
                .setParameter("password", password)
                .getResultList();
        return users.isEmpty() ? null : users.get(0);
    }

    public User findByToken(String authToken) {
        List<User> users = em.createNamedQuery("User.findByToken", User.class)
                .setParameter("token", authToken)
                .getResultList();
        return users.isEmpty() ? null : users.get(0);
    }

    public Salt createUser(String firstName, String lastName, String email, String phone) {
        try {
            User user = findByEmail(email);
            if (user == null) {
                user = new User();
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setPhone(phone);

                Salt salt = new Salt();
                user.setSalt(salt.toString());

                Collection<Role> roles = em.createNamedQuery("Role.findByName", Role.class)
                        .setParameter("name", "user")
                        .getResultList();
                user.setRoleCollection(roles);

                em.persist(user);
                em.flush();
                return salt;
            } else {
                return null;
            }
        } catch (Exception E) {
            System.err.println(E);
            return null;
        }
    }

    public boolean setUserPassword(String email, String password) {
        boolean result = false;
        try {
            User user = findByEmail(email);
            if (user != null) {
                if (user.getPassword() != null) {
                    return result;
                }
                user.setPassword(password);
                em.merge(user);
                result = true;
            }

        } catch (Exception E) {
            System.err.println(E);
        }
        return result;
    }

    public Salt getSalt(String email) {
        User user = findByEmail(email);
        Salt salt;
        if (user != null) {
            salt = new Salt(user.getSalt());
        } else {
            salt = new Salt();
            salt.setConstFakeSalt(email);
        }
        return salt;
    }
    
    public Collection<Order> getCustomerOrderCollection(String authToken) {
        User user = findByToken(authToken);
        if (user != null) {
            em.refresh(user);
            return user.getCustomerOrderCollection();
        } else
        {
            return Collections.EMPTY_LIST;
        }
    
    }

}
