package com.fifth.auction.emtity;

import java.io.Serializable;
import java.util.Date;

public class AdminLog implements Serializable {
    private Integer uid;
    private Integer userid;
    private String username;
    private Date usercreatetime;
    private Integer adminid;
    private String adminname;
    private String data;
    private Date time;

    @Override
    public String toString() {
        return "AdminLog{" +
                "uid=" + uid +
                ", userid=" + userid +
                ", username='" + username + '\'' +
                ", usercreatetime=" + usercreatetime +
                ", adminid=" + adminid +
                ", adminname='" + adminname + '\'' +
                ", data='" + data + '\'' +
                ", time=" + time +
                '}';
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getUsercreatetime() {
        return usercreatetime;
    }

    public void setUsercreatetime(Date usercreatetime) {
        this.usercreatetime = usercreatetime;
    }

    public Integer getAdminid() {
        return adminid;
    }

    public void setAdminid(Integer adminid) {
        this.adminid = adminid;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
