package com.fifth.auction.controller;

import com.fifth.auction.Utils.JSONResult;
import com.fifth.auction.emtity.Auction;
import com.fifth.auction.service.IAuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@RestController
@RequestMapping("auctions")
public class AuctionController extends BaseController{
    @Autowired
    private IAuctionService auctionService;

    /**
     * 发布拍卖
     * @param auction 拍卖信息
     * @param file 拍卖图片
     * @param session session
     * @return JSONResult
     */
    @RequestMapping("add_auction")
    public JSONResult<Void> addAuction(Auction auction, MultipartFile file, HttpSession session){
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        auction.setOwnerid(uid);
        auction.setOwnername(username);
        auctionService.addAuction(auction,file,session);
        return new JSONResult<>(OK);
    }

    /**
     * 根据拍卖品ID获取拍卖信息
     * @param aid 拍卖品id
     * @return JSONResult
     */
    @RequestMapping("get_auction")
    public JSONResult<Auction> getAuction(Integer aid){
        Auction data=auctionService.getAuctionFormAid(aid);
        return new JSONResult<>(OK,data);
    }

    /**
     * 获取全部拍卖品
     * @return JSONResult
     */
    @RequestMapping("get_all_auctions")
    public JSONResult<ArrayList<Auction>> getAllAuction(){
        ArrayList<Auction> data = auctionService.getAllAuction();
        return new JSONResult<>(OK,data);
    }

    /**
     * 根据种类获取拍卖品
     * @param category 种类
     * @return JSONResult
     */
    @RequestMapping("get_auctions_by_category")
    public JSONResult<ArrayList<Auction>> getAuctionsByIsend(Integer category){
        ArrayList<Auction> data = auctionService.findByCategory(category);
        return new JSONResult<>(OK,data);
    }

    /**
     * 根据是否结束获取拍卖
     * @param isend 1:已结束 0:未结束
     * @return JSONResult
     */
    @RequestMapping("get_auctions_by_isend")
    public JSONResult<ArrayList<Auction>> getIsendByAuctions(Integer isend){
        ArrayList<Auction> data = auctionService.findByisend(isend);
        return new JSONResult<>(OK,data);
    }

    /**
     * 判断拍卖是否结束
     * @param auction 拍卖信息
     * @return JSONResult
     */
    @RequestMapping("isend")
    public JSONResult<Void> setisend(Auction auction){
        auctionService.Isend(auction);
        return new JSONResult<>(OK);
    }

    /**
     * 更新最高出价者
     * @param auction 拍卖信息
     * @param session session
     * @return JSONResult
     */
    @RequestMapping("auction")
    public JSONResult<Void> updateHighestBidder(Auction auction,HttpSession session){
        auction.setCurrentuser(getUsernameFromSession(session));
        auction.setCurrentserid(getUidFromSession(session));
//        System.out.println(auction);
        auctionService.updateHighestBidder(auction);
        return new JSONResult<>(OK);
    }

    /**
     * 查找当前登录用户发布的拍卖品
     * @param session session
     * @return JSONResult
     */
    @RequestMapping("find_by_ownerid")
    public JSONResult<ArrayList<Auction>> findByOwnerid(HttpSession session){
        Integer uid = getUidFromSession(session);
        if (uid == null ){
            return null;
        }
        ArrayList<Auction> data = auctionService.findByownerid(uid);
        return new JSONResult<>(OK,data);
    }

    /**
     * 查找当前用户已拍卖到的拍卖品
     * @param session session
     * @return JSONResult
     */
    @RequestMapping("find_by_isend_currentserid")
    public JSONResult<ArrayList<Auction>> findByisendCid(HttpSession session){
        Integer uid = getUidFromSession(session);
        if (uid == null ){
            return null;
        }
        ArrayList<Auction> data = auctionService.findByendCid(uid);
        return new JSONResult<>(OK,data);
    }

}
