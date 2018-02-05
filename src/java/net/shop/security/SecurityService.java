package net.shop.security;


public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
