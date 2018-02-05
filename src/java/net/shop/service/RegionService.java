package net.shop.service;

import net.shop.model.User;
import net.shop.model.region.City;
import net.shop.model.region.Country;
import net.shop.model.region.Region;

import java.util.List;

public interface RegionService {
    public List<Country> getAllCountries();
    public List<Region> getRegionsFromCountry(int country_id);
    public List<City> getCitiesFromRegion(int region_id);
    public City getCityById(int city_id);

    public List<User> getUsersFromCountry(Country country);
    public List<User> getUsersFromRegion(Region region);
    public List<User> getUsersFromCity(City city);

}