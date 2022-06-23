package com.fifth.auction.service;


import com.fifth.auction.emtity.Auction;

public interface IAuctionService {

    void addAuction(Auction auction);

    Auction getAuctionFormAid(Integer aid);

}
