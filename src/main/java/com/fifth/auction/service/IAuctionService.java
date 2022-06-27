package com.fifth.auction.service;


import com.fifth.auction.emtity.Auction;

import java.util.ArrayList;

public interface IAuctionService {

    void addAuction(Auction auction);

    Auction getAuctionFormAid(Integer aid);

    ArrayList<Auction> getAllAuction();

    ArrayList<Auction> findByCategory(Integer category);

    ArrayList<Auction> findByisend(Integer issend);

    ArrayList<Auction> findByownerid(Integer ownerid);

    void Isend(Auction auction);

    void updateHighestBidder(Auction auction);



}
