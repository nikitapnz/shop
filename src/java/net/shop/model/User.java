package net.shop.model;

import net.shop.model.region.City;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Pattern(regexp = "^[a-zA-Z0-9_]{6,12}$")
    @Column(name = "username")
    private String username;

  //  @Pattern(regexp = "^[a-zA-Z0-9_]{7,100}$")
    @Column(name = "password")
    private String password; //todo

    @Column(name = "phone")
    private String phone;

    @Pattern(regexp = "^[a-zA-Zа-яА-Яё]{2,15}$")
    @Column(name = "name")
    private String name;

    @Pattern(regexp = "^[a-zA-Zа-яА-Яё]{2,15}$")
    @Column(name = "lastName")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "email")
    private String email;

    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @NotNull
    @Transient
    private int cityid;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    private Sms sms;

    public Sms getSms() {
        return sms;
    }

    public void setSms(Sms sms) {
        this.sms = sms;
    }

    //    @Column(name = "roles")
//    private String roles;
//
//    @Column(name = "savedThings")
//    private String savedThings;
//
//    @Column(name = "history")
//    private String history;


    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCityid() {
        return cityid;
    }

    public void setCityid(int cityid) {
        this.cityid = cityid;
    }


    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city=" + city +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                ", cityid=" + cityid +
                '}';
    }
}