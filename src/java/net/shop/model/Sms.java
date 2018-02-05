package net.shop.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import java.util.Date;


@Entity
@Table(name = "sms")
public class Sms {

    @Id
    @Column(name = "id")
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ip")
    private String ip;

    @Pattern(regexp = "^7[0-9]{10}$")
    @Column(name = "phone")
    private String phone;

    @Column(name = "code")
    private int code;

    @Column(name = "attempts")
    private int attempts = 0;

    @Column(name = "startDate")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    @Override
    public String toString() {
        return "Sms{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", phone='" + phone + '\'' +
                ", code=" + code +
                ", attempts=" + attempts +
                ", startDate=" + startDate +
                '}';
    }
}