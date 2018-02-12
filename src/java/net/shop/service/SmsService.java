package net.shop.service;

import net.shop.model.Sms;
import net.shop.model.User;
import org.springframework.validation.BindingResult;

import java.util.HashMap;

public interface SmsService {
    public int createCode();
    public HashMap<String, String> addCode(String ip, Sms sms, User user);
    public HashMap<String, String> checkCode(String ip, Sms sms, User user);
}