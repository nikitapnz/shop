package net.shop.model;

import net.shop.model.region.City;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
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

    @Pattern(regexp = "^[a-zA-Z0-9_]{6,12}$",
            message = "Size username must be in range 6-12. Available symbols is A-Z, a-z, 0-9 and '_'.")
    @Column(name = "username")
    private String username;

    //   @Pattern(regexp = "^[a-zA-Z0-9_]{7,20}$",
    //           message = "Size password must be in range 7-20. Available symbols is A-Z, a-z, 0-9 and '_'.")
    @Column(name = "password")
    private String password; //todo

    @Pattern(regexp = "^7[0-9]{10}$",
            message = "The phone number must start at 7 and have 11 digits.")
    @Column(name = "phone")
    private String phone;

    @Pattern(regexp = "^[a-zA-Zа-яА-Яё]{2,15}$",
            message = "Size name must be in range 2-15. Available symbols is A-Z, a-z, а-я, А-Я.")
    @Column(name = "name")
    private String name;

    @Pattern(regexp = "^[a-zA-Zа-яА-Яё]{2,15}$",
            message = "Size last name must be in range 2-15. Available symbols is A-Z, a-z, а-я, А-Я.")
    @Column(name = "lastName")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @NotNull
    @Transient
    private int cityid;

    @NotNull
    @Transient
    private int phoneCode;

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

    public int getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(int phoneCode) {
        this.phoneCode = phoneCode;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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
                '}';
    }
}