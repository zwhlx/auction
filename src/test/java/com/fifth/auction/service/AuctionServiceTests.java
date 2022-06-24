package com.fifth.auction.service;

import com.fifth.auction.emtity.Auction;
import com.fifth.auction.service.impl.AuctionServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AuctionServiceTests {

    @Autowired
    private IAuctionService auctionService;

    @Test
    public void addAuction(){
        Auction auction = new Auction();
        auction.setCategory(1);
        auction.setOwnerid(11);
        auction.setOwnername("admin");
        auction.setAuctionname("test测试");
        auction.setEndtime(new Date());
        auction.setStartprice(0.33);
        auctionService.addAuction(auction);
    }

    @Test
    public void getAuctionFormAid(){
        System.out.println(auctionService.getAuctionFormAid(1));
    }

    @Test
    public void getAllAuction(){
        System.out.println(auctionService.getAllAuction());
    }
    @Test
    public void findByCategory(){
        System.out.println(auctionService.findByCategory(1));
    }
    @Test
    public void findByissend(){
        System.out.println(auctionService.findByisend(0));
    }
}
