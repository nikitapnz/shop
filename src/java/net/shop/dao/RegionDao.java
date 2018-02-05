package net.shop.dao;

import net.shop.model.region.*;
import net.shop.model.User;

import java.util.List;

public interface RegionDao {

        public List<User> getUsersFromCity(int id);
        public List<User> getUsersFromRegion(int id);
        public List<User> getUsersFromCountry(int id);

        public City getCityById(int id);
        public Region getRegionById(int id);
        public Country getCountryById(int id);

        public List<City> getAllCitiesFromRegion(int id);
        public List<Region> getAllRegionsFromCountry(int id);

        public List<Country> getAllCountries();
}