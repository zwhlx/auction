package com.fifth.auction.emtity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private Integer uid;
    private String username;
    private String password;
    private String idcard;
    private String name;
    private String mobilephone;
    private String address;
    private String email;
    private Integer qq;
    private Date createtime;
    private Integer admin;
    private String salt;

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
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

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getQq() {
        return qq;
    }

    public void setQq(Integer qq) {
        this.qq = qq;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }


    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", idcard='" + idcard + '\'' +
                ", name='" + name + '\'' +
                ", mobilephone='" + mobilephone + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", qq=" + qq +
                ", createtime=" + createtime +
                ", admin=" + admin +
                ", salt='" + salt + '\'' +
                '}';
    }
}
