package net.shop.dao;

import net.shop.model.region.Country;
import net.shop.model.region.Region;
import net.shop.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;

import java.util.List;

@Repository
@PropertySource("classpath:properties/user.properties")
public class UserDaoImpl implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    private Environment env;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean addUser(User user, BindingResult result) {
        Session session = this.sessionFactory.getCurrentSession();
        if (checkExist("username", user.getUsername(), session)) {
            result.rejectValue("username", "username.user", env.getProperty("Exist.user.username"));
            return false;
        }
        System.out.println(user.toString());
        session.persist(user);
        return true;
    }


    public void updateUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(user);
    }


    public void removeUser(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, new Integer(id));

        if (user != null) {
            session.delete(user);
        }
    }


    public User getUserById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, new Integer(id));

        return user;
    }

    public boolean checkExist(String field, String val, Session session) {
        boolean res = true;
        User user = null;
        try {
            user = (User) session
                    .createQuery("from User where " + field + "=:val")
                    .setParameter("val", val)
                    .uniqueResult();
            if (user == null)
                res = false;
        } catch (Exception e) {
            res = false;
        }
        System.out.println(res);
        return res;
    }

    public User findByUsername(String username) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = null;
        try {
            user = (User) session
                    .createQuery("from User where username=:username")
                    .setParameter("username", username)
                    .uniqueResult();
        } catch (Exception e) {

        }
        return user;
    }

    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> userList = session.createQuery("from User").list();
        return userList;
    }
}