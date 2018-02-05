package net.shop.dao;

import net.shop.model.User;
import org.hibernate.Session;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface UserDao {
    public boolean addUser(User user, BindingResult result);

    public void updateUser(User user);

    public void removeUser(int id);

    public User getUserById(int id);

    public User findByUsername(String username);

    public List<User> listUsers();

    public boolean checkExist(String field, String val, Session session);
}