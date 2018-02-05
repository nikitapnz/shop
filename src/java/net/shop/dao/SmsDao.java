package net.shop.dao;

import net.shop.model.Sms;
import org.springframework.validation.BindingResult;

import java.util.HashMap;

public interface SmsDao {
    public HashMap<String, String> createSms(Sms sms);
    public boolean checkCode(String ip, String phone, int code, BindingResult result);
}