package net.shop.service;

import net.shop.dao.RoleDao;
import net.shop.dao.UserDao;
import net.shop.model.Role;
import net.shop.model.User;
import net.shop.model.region.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private RegionService regionService;
    private SmsService SmsService;
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void setSmsService(net.shop.service.SmsService smsService) {
        SmsService = smsService;
    }

    public void setRegionService(RegionService regionService) {
        this.regionService = regionService;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional
    public boolean addUser(User user, BindingResult result) {
        City city = regionService.getCityById(user.getCityid());
        if (city == null)
            return false;
        user.setCity(city);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<Role>();
        roles.add(roleDao.getOne(1));
        user.setRoles(roles);
        System.out.println(roles.toString());
        return this.userDao.addUser(user, result);
    }


    @Transactional
    public void updateUser(User user) {
        this.userDao.updateUser(user);
    }


    @Transactional
    public void removeUser(int id) {
        this.userDao.removeUser(id);
    }

    @Transactional
    public User getUserById(int id) {
        return this.userDao.getUserById(id);
    }

    @Transactional
    public List<User> listUsers() {
        return this.userDao.listUsers();
    }

}