package net.shop.service;

import net.shop.dao.RegionDao;
import net.shop.model.User;
import net.shop.model.region.City;
import net.shop.model.region.Country;
import net.shop.model.region.Region;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class RegionServiceImpl implements RegionService {
    private RegionDao regionDao;

    @Transactional
    public List<Country> getAllCountries() {
        return regionDao.getAllCountries();
    }

    @Transactional
    public List<Region> getRegionsFromCountry(int id) {
        return regionDao.getAllRegionsFromCountry(id);
    }

    @Transactional
    public List<City> getCitiesFromRegion(int id) {
        return regionDao.getAllCitiesFromRegion(id);
    }

    @Transactional
    public List<User> getUsersFromCountry(Country country) {
        return regionDao.getUsersFromCountry(country.getId());
    }

    @Transactional
    public List<User> getUsersFromRegion(Region region) {
        return regionDao.getUsersFromRegion(region.getId());
    }

    @Transactional
    public List<User> getUsersFromCity(City city) {
        return regionDao.getUsersFromCity(city.getId());
    }

    @Transactional
    public void setRegionDao(RegionDao regionDao) {
        this.regionDao = regionDao;
    }

    @Transactional
    public City getCityById(int city_id) {
        return regionDao.getCityById(city_id);
    }
}