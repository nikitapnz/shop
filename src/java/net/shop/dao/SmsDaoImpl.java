package net.shop.dao;

import net.shop.model.Sms;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;

import java.util.HashMap;

@Repository
public class SmsDaoImpl implements SmsDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void createSms(Sms sms) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(sms);
    }

    public void deleteSms(Sms sms){
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(sms);
    }

    public void updateSms(Sms sms) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(sms);
    }

    public Sms existRow(String field, String val) {
        Session session = this.sessionFactory.getCurrentSession();
        Sms sms = null;
        try {
            sms = (Sms) session
                    .createQuery("from Sms where "+field+"=:val")
                    .setParameter("val", val)
                    .uniqueResult();
        } catch (Exception e) {
            //TODO
        }
        return sms;
    }

}