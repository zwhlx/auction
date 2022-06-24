package com.fifth.auction.service.impl;

import com.fifth.auction.emtity.Auction;
import com.fifth.auction.emtity.User;
import com.fifth.auction.mapper.AuctionMapper;
import com.fifth.auction.mapper.UserMapper;
import com.fifth.auction.service.IAuctionService;
import com.fifth.auction.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class AuctionServiceImpl implements IAuctionService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AuctionMapper auctionMapper;

    @Override
    public void addAuction(Auction auction) {
        Date date = auction.getEndtime();
        if (date.before(new Date())){
            throw new AuctionTimeException("当前结束拍卖时间不大于开始拍卖时间");
        }
        if (auction.getCategory()==null||
                auction.getOwnerid()==null||
                auction.getOwnername()==null||
                auction.getAuctionname()==null||
                auction.getEndtime()==null||
                auction.getStartprice()==null){
            throw new UpdateException("数据为空");
        }
        Integer ownerid = auction.getOwnerid();
        User result = userMapper.findByUid(ownerid);
        if (result == null) {
            throw new UserNotExistException("用户不存在");
        }
        Integer rows = auctionMapper.insert(auction);
        if (rows!=1){
            throw new InserException("发布拍卖品失败");
        }
    }

    @Override
    public Auction getAuctionFormAid(Integer aid) {
        Auction auction = auctionMapper.findByAid(aid);
        if (auction==null){
            throw new AuctionNotExistException("拍卖品不存在");
        }
        return auction;
    }

    @Override
    public ArrayList<Auction> getAllAuction() {
        return auctionMapper.getAll();
    }

    @Override
    public ArrayList<Auction> findByCategory(Integer category) {
        return auctionMapper.findByCategory(category);

    }

    @Override
    public ArrayList<Auction> findByisend(Integer isend) {
        return auctionMapper.findByisend(isend);
    }

    @Override
    public ArrayList<Auction> findByownerid(Integer ownerid) {
        return auctionMapper.findByOwnerid(ownerid);
    }
}
