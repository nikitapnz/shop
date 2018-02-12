package net.shop.service;

import net.shop.dao.SmsDao;
import net.shop.dao.UserDao;
import net.shop.model.Sms;
import net.shop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Random;

//@PropertySource("classpath:properties/sms.properties")
public class SmsServiceImpl implements SmsService {
    private SmsDao smsDao;

//    @Value("${Sms.config.numberOfAttempts}")
    private int numberOfAttempts = 3;

    @Autowired
    private UserDao userDao;
    public void setSmsDao(SmsDao smsDao) {
        this.smsDao = smsDao;
    }

    @Transactional
    public int createCode() {
        Random r = new Random();
        return r.nextInt(8999) + 1000;
    }

//    public boolean sendSms(String phone, int code){
//        return true;
//    }

    @Transactional
    public HashMap<String, String> addCode(String ip, Sms sms, User user) {
        String valueJson = "createSms";
        String phone = sms.getPhone();
        try {

            if (smsDao.existRow("phone", phone) != null)
                return createJson(valueJson, "false",
                        "The phone already exist");

            if (smsDao.existRow("ip", ip) != null)
                return createJson(valueJson, "false",
                        "The ip already exist");


            int code = createCode();
            sms.setIp(ip);
            sms.setCode(code);
            sms.setUser(user);

            smsDao.createSms(sms);
            return createJson(valueJson, "true",
                    "Sms is created");
        } catch (Exception e) {
            return createJson(valueJson, "false",
                    "Internal error");
        }
    }

    @Transactional
    public HashMap<String, String> checkCode(String ip, Sms smsReq, User user) {
        String valueJson = "checkCode";
        Sms sms = user.getSms();
        if (sms == null) {
            return createJson(valueJson, "false",
                    "Sms doesn't exist");
        }

        if (sms.getAttempts() == numberOfAttempts) {
            return createJson(valueJson, "false",
                    "Count of attempts is ended");
        }

        if (sms.getCode() == smsReq.getCode()) {
            user.setPhone(sms.getPhone());
            userDao.updateUser(user);
            smsDao.deleteSms(sms); // если есть код, удалем его и ретурн тру
            return createJson(valueJson, "true",
                    "Correct code");
        } else {
            sms.setAttempts(sms.getAttempts() + 1);
            smsDao.updateSms(sms);
            return createJson(valueJson, "false",
                    "the number of attempts is reduced by one, you have "
                            + Integer.toString(numberOfAttempts - sms.getAttempts()) + " more attempts");
        }
    }

    private HashMap<String, String> createJson(String value, String res, String description) {
        HashMap<String, String> ans = new HashMap<String, String>();
        ans.put("type", value);
        ans.put("result", res);
        ans.put("description", description);
        return ans;
    }


}