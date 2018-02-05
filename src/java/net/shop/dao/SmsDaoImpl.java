package net.shop.dao;

import net.shop.model.Sms;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SmsDaoImpl implements SmsDao {
    private SessionFactory sessionFactory;
    private UserDao userDao;
    private int numberOfAttempts = 3; // todo

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public HashMap<String, String> createSms(Sms sms) {
        boolean result = false;
        String valueJson = "createSms";

        try {
            Session session = this.sessionFactory.getCurrentSession();

            if (userDao.checkExist("phone", sms.getPhone(), session)) {
                return createJson(valueJson, "false",
                        "The phone already exist");
            }

            if (existRow(sms.getIp(), sms.getPhone(), session, "or") == null) {
                session.save(sms);
                return createJson(valueJson, "true",
                        "Sms is created");
            } else {
                return createJson(valueJson, "false",
                        "sms already exists");
            }
        } catch (Exception e) {
            return createJson(valueJson, "false",
                    "Internal error");

        }
    }


    public boolean checkCode(String ip, String phone, int code, BindingResult result) {
        Session session = this.sessionFactory.getCurrentSession();
        String valueJson = "checkCode";
        Sms sms = existRow(ip, phone, session, "and");
        if (sms == null) {
            result.rejectValue("phoneCode", "phoneCode.user", "Sms doesn't exist");
            return false;
        }

        if (sms.getAttempts() == numberOfAttempts) {
            result.rejectValue("phoneCode", "phoneCode.user", "count of attempts is ended");
            return false;
        }

        if (sms.getCode() == code) {
            session.delete(sms); // если есть код, удалем его и ретурн тру
            return true;
        } else {
            sms.setAttempts(sms.getAttempts() + 1);
            result.rejectValue("phoneCode", "phoneCode.user",
                    "the number of attempts is reduced by one, you have "
                            + Integer.toString(numberOfAttempts - sms.getAttempts()) + " more attempts");
            return false;
        }
    }

    private Sms existRow(String ip, String phone, Session session, String sign) {
        Sms sms = null;
        try {
            sms = (Sms) session
                    .createQuery("from Sms where ip=:ip " + sign + " phone=:phone")
                    .setParameter("ip", ip)
                    .setParameter("phone", phone)
                    .uniqueResult();
        } catch (Exception e) {
            //TODO
        }
        return sms;
    }


    private HashMap<String, String> createJson(String value, String res, String description) {
        HashMap<String, String> ans = new HashMap<String, String>();
        ans.put("type", value);
        ans.put("result", res);
        ans.put("description", description);
        return ans;
    }

}