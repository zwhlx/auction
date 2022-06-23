package com.fifth.auction.service;


import com.fifth.auction.emtity.Auction;

import java.util.ArrayList;

public interface IAuctionService {

    void addAuction(Auction auction);

    Auction getAuctionFormAid(Integer aid);

    ArrayList<Auction> getAllAuction();

}
