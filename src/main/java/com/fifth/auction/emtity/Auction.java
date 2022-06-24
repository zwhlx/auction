package com.fifth.auction.emtity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class Auction implements Serializable {
    private Integer aid;
    private Integer isend;
    private Integer category;
    private Integer ownerid;
    private String ownername;
    private String auctionname;
    private String auctiondetail;
    private String img;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date starttime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endtime;
    private Double startprice;
    private Double currentprice;
    private String currentuser;
    private Integer currentserid;

    @Override
    public String toString() {
        return "Auction{" +
                "aid=" + aid +
                ", isend=" + isend +
                ", category=" + category +
                ", ownerid=" + ownerid +
                ", ownername='" + ownername + '\'' +
                ", auctionname='" + auctionname + '\'' +
                ", auctiondetail='" + auctiondetail + '\'' +
                ", img='" + img + '\'' +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                ", startprice=" + startprice +
                ", currentprice=" + currentprice +
                ", currentuser='" + currentuser + '\'' +
                ", currentserid=" + currentserid +
                '}';
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getIsend() {
        return isend;
    }

    public void setIsend(Integer isend) {
        this.isend = isend;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(Integer ownerid) {
        this.ownerid = ownerid;
    }

    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    public String getAuctionname() {
        return auctionname;
    }

    public void setAuctionname(String auctionname) {
        this.auctionname = auctionname;
    }

    public String getAuctiondetail() {
        return auctiondetail;
    }

    public void setAuctiondetail(String auctiondetail) {
        this.auctiondetail = auctiondetail;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Double getStartprice() {
        return startprice;
    }

    public void setStartprice(Double startprice) {
        this.startprice = startprice;
    }

    public Double getCurrentprice() {
        return currentprice;
    }

    public void setCurrentprice(Double currentprice) {
        this.currentprice = currentprice;
    }

    public String getCurrentuser() {
        return currentuser;
    }

    public void setCurrentuser(String currentuser) {
        this.currentuser = currentuser;
    }

    public Integer getCurrentserid() {
        return currentserid;
    }

    public void setCurrentserid(Integer currentserid) {
        this.currentserid = currentserid;
    }
}

