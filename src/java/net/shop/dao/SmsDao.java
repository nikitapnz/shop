package net.shop.dao;

import net.shop.model.Sms;
import org.springframework.validation.BindingResult;

import java.util.HashMap;

public interface SmsDao {
    public void createSms(Sms sms);
    public Sms existRow(String field, String value);
    public void deleteSms(Sms sms);
    public void updateSms(Sms sms);
}