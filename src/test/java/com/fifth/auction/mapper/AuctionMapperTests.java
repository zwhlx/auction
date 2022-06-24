package com.fifth.auction.mapper;

import com.fifth.auction.emtity.Auction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AuctionMapperTests {

    @Autowired
    private AuctionMapper auctionMapper;

    @Test
    public void insert(){
        Auction auction = new Auction();
        auction.setCategory(1);
        auction.setOwnerid(11);
        auction.setOwnername("admin");
        auction.setAuctionname("test测试");
        auction.setEndtime(new Date());
        auction.setStartprice(0.33);
        Integer rows = auctionMapper.insert(auction);
        System.out.println(rows);
    }

    @Test
    public void findByAid(){
        System.out.println(auctionMapper.findByAid(1));
    }
    @Test
    public void getAll(){
        System.out.println(auctionMapper.getAll());
    }
    @Test
    public void findByCategory(){
        System.out.println(auctionMapper.findByCategory(1));
    }

    @Test
    public void delete(){
        System.out.println(auctionMapper.delete(2));
    }

    @Test
    public void findByisend(){
        System.out.println(auctionMapper.findByisend(0));
    }

    @Test
    public void findByOwnerid(){
        System.out.println(auctionMapper.findByOwnerid(11));
    }



}
