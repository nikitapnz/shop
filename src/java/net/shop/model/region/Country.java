package net.shop.model.region;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.shop.model.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "countries")
public class Country {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "name")
    String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "country")
    @JsonIgnore
    List<Region> regions;

    @JsonIgnore
    public List<User> getUsers(){
        List<User> users = new ArrayList<User>();
        for (Region region: regions){
            users.addAll(region.getUsers());
        }
        return users;
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

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}