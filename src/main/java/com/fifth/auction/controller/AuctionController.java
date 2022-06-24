package com.fifth.auction.controller;

import com.fifth.auction.Utils.JSONResult;
import com.fifth.auction.emtity.Auction;
import com.fifth.auction.service.IAuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@RestController
@RequestMapping("auctions")
public class AuctionController extends BaseController{
    @Autowired
    private IAuctionService auctionService;

    @RequestMapping("add_auction")
    public JSONResult<Void> addAuction(Auction auction, HttpSession session){
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        auction.setOwnerid(uid);
        auction.setOwnername(username);
        auctionService.addAuction(auction);
        return new JSONResult<>(OK);
    }

    @RequestMapping("get_auction")
    public JSONResult<Auction> getAuction(Integer aid){
        Auction data=auctionService.getAuctionFormAid(aid);
        return new JSONResult<>(OK,data);
    }

    @RequestMapping("get_all_auctions")
    public JSONResult<ArrayList<Auction>> getAllAuction(){
        ArrayList<Auction> data = auctionService.getAllAuction();
        return new JSONResult<>(OK,data);
    }

    @RequestMapping("get_auctions_by_category")
    public JSONResult<ArrayList<Auction>> getAuctionsByIsend(Integer category){
        ArrayList<Auction> data = auctionService.findByCategory(category);
        return new JSONResult<>(OK,data);
    }

    @RequestMapping("get_auctions_by_isend")
    public JSONResult<ArrayList<Auction>> getIsendByAuctions(Integer isend){
        ArrayList<Auction> data = auctionService.findByisend(isend);
        return new JSONResult<>(OK,data);
    }

}
