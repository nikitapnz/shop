package net.shop.dao;

import net.shop.model.Sms;
import net.shop.model.User;
import net.shop.model.region.City;
import net.shop.model.region.Country;
import net.shop.model.region.Region;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RegionDaoImpl implements RegionDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<User> getUsersFromCity(int id) {
        City city = getCityById(id);
        if (city == null) return null;

        return city.getUsers();
    }

    public List<User> getUsersFromRegion(int id) {
        Region region = getRegionById(id);
        if (region == null) return null;

        return region.getUsers();
    }

    public List<User> getUsersFromCountry(int id) {
        Country country = getCountryById(id);
        if (country == null) return null;

        return country.getUsers();
    }

    public City getCityById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        City city = null;
        try {
            city = (City) session
                    .createQuery("from City where id=:id")
                    .setParameter("id", id)
                    .uniqueResult();
        } catch (Exception e) {
            //TODO
        }
        return city;
    }

    public Region getRegionById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Region region = (Region) session.load(Region.class, new Integer(id));

        return region;
    }

    public Country getCountryById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Country country = (Country) session.load(Country.class, new Integer(id));

        return country;
    }

    public List<City> getAllCitiesFromRegion(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        List<City> cityList = null;
        try {
            Region region = (Region) session.load(Region.class, new Integer(id));
            cityList = region.getCities();
        } catch (Exception e) {
            //TODO
        }
        return cityList;
    }

    public List<Region> getAllRegionsFromCountry(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Region> regionList = null;
        try {
            Country country = (Country) session.load(Country.class, new Integer(id));
            regionList = country.getRegions();
        } catch (Exception e) {
            // TODO
        }
        return regionList;
    }

    public List<Country> getAllCountries() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Country> userList = session.createQuery("from Country").list();
        return userList;
    }

}