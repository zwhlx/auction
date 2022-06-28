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

    /**
     * 添加拍卖
     * @param auction 拍卖品信息
     */
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

    /**
     * 根据拍卖品id查询拍卖品
     * @param aid 拍卖品id
     * @return 拍卖品信息
     */
    @Override
    public Auction getAuctionFormAid(Integer aid) {
        Auction auction = auctionMapper.findByAid(aid);
        if (auction==null){
            throw new AuctionNotExistException("拍卖品不存在");
        }
        return auction;
    }

    /**
     * 获取全部拍卖
     * @return 存有所有拍卖品的ArrayList
     */
    @Override
    public ArrayList<Auction> getAllAuction() {
        return auctionMapper.getAll();
    }

    /**
     * 根据拍卖类型进行查找
     * @param category 拍卖类型
     * @return  存有 所查询类型的拍卖品的ArrayList
     */
    @Override
    public ArrayList<Auction> findByCategory(Integer category) {
        return auctionMapper.findByCategory(category);

    }

    /**
     * 根据拍卖是否已完成进行查找
     * @param isend 1-已完成  2-未完成
     * @return 存有 所查询的拍卖品的ArrayList
     */
    @Override
    public ArrayList<Auction> findByisend(Integer isend) {
        return auctionMapper.findByisend(isend);
    }

    /**
     * 根据用户id查询所属拍卖
     * @param ownerid 用户id
     * @return  改用户的所有拍卖品
     */
    @Override
    public ArrayList<Auction> findByownerid(Integer ownerid) {
        return auctionMapper.findByOwnerid(ownerid);
    }

    @Override
    public ArrayList<Auction> findByendCid(Integer currentserid) {
        return auctionMapper.findByisendCid(1,currentserid);
    }

    /**
     * 更新拍卖状态
     * @param auction 拍卖信息
     */
    @Override
    public void Isend(Auction auction) {
        Integer aid=auction.getAid();
        auction=auctionMapper.findByAid(aid);
        if (auction==null){throw new AuctionNotExistException("找不到拍卖品");}
        if (new Date().getTime()>=auction.getEndtime().getTime()||auction.getIsend()==0){
            auction.setIsend(1);
        }else {
            auction.setIsend(0);
        }
        auctionMapper.updateIsend(auction);
    }

    /**
     * 更新最高价者
     * @param auction 拍卖信息
     */
    @Override
    public void updateHighestBidder(Auction auction) {
        Auction data = auctionMapper.findByAid(auction.getAid());
        if (data==null){
            throw new AuctionNotExistException("拍卖不存在");
        }
        if (data.getIsend()!=0||new Date().getTime()>=data.getEndtime().getTime()){
            throw new AuctionTimeException("拍卖已结束");
        }
        if (data.getCurrentprice()==null){
            if (auction.getCurrentprice()<=data.getStartprice()){
                throw new AuctionPriceException("出价小于当前价");
            }
        }else{
            if (auction.getCurrentprice()<=data.getCurrentprice()){
                throw new AuctionPriceException("出价小于当前价");
            }
        }

        auctionMapper.updateHighestBidder(auction);
    }
}
