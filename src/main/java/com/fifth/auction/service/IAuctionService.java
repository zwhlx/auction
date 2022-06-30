package com.fifth.auction.service;


import com.fifth.auction.emtity.Auction;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public interface IAuctionService {

    /**
     * 发布拍卖
     * @param auction 拍卖信息
     * @param file 拍卖品图片
     * @param session 用户session
     */
    void addAuction(Auction auction, MultipartFile file, HttpSession session);

    /**
     * 使用拍卖品id获取拍卖品信息
     * @param aid 拍卖品id
     * @return 拍卖信息
     */
    Auction getAuctionFormAid(Integer aid);

    /**
     * 获取全部拍卖品信息存于ArrayList集合
     * @return ArrayList集合
     */
    ArrayList<Auction> getAllAuction();

    /**
     * 根据种类获取拍卖品信息
     * @param category 拍卖种类
     * @return 存有拍卖品信息的ArrayList集合
     */
    ArrayList<Auction> findByCategory(Integer category);

    /**
     * 根据拍卖是否已结束获取拍卖信息
     * @param issend 1:已结束 0:未结束
     * @return 存有拍卖品信息的ArrayList集合
     */
    ArrayList<Auction> findByisend(Integer issend);

    /**
     * 根据发布者id获取拍卖信息
     * @param ownerid 发布者id
     * @return 存有拍卖品信息的ArrayList集合
     */
    ArrayList<Auction> findByownerid(Integer ownerid);

    /**
     * 查询用户已拍卖到的拍卖品
     * @param currentserid 获得者ID
     * @return 存有拍卖品信息的ArrayList集合
     */
    ArrayList<Auction> findByendCid(Integer currentserid);

    /**
     * 判断拍卖是否已结束
     * @param auction
     */
    void Isend(Auction auction);

    /**
     * 更新最高出价者
     * @param auction 拍卖信息
     */
    void updateHighestBidder(Auction auction);



}
