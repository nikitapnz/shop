package net.shop.service;

import net.shop.model.User;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface UserService {
    public boolean addUser(User user, BindingResult result);

    public void updateUser(User user);

    public void removeUser(int id);

    public User getUserById(int id);

    public List<User> listUsers();
}