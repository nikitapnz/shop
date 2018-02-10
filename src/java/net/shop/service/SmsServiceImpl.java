package net.shop.service;

import net.shop.dao.SmsDao;
import net.shop.model.Sms;
import net.shop.model.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Random;

public class SmsServiceImpl implements SmsService {
    private SmsDao smsDao;

    public void setSmsDao(SmsDao smsDao) {
        this.smsDao = smsDao;
    }

    @Transactional
    public int createCode() {
        Random r = new Random();
        return r.nextInt(8999) + 1000;
    }

    @Transactional
    public HashMap<String, String> addCode(String ip, Sms sms) {
        sms.setIp(ip);
        sms.setCode(createCode());
        System.out.println(sms.toString());
        return smsDao.createSms(sms);
    }

    @Transactional
    public boolean checkCode(String ip, User user, BindingResult result) {
        //return smsDao.checkCode(ip, user.getPhone(), user.getPhoneCode(), result);
        return true;
    }
}