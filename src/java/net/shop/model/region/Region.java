package net.shop.model.region;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.shop.model.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "regions")
public class Region {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "name")
    String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    @JsonIgnore
    private Country country;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "region")
    @JsonIgnore
    List<City> cities;

    @JsonIgnore
    public List<User> getUsers(){
        List<User> users = new ArrayList<User>();
        for (City city: cities){
            users.addAll(city.getUsers());
        }
        return users;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}