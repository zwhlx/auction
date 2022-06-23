package com.fifth.auction.mapper;

import com.fifth.auction.emtity.Auction;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionMapper {
    //-------  增  --
    Integer insert(Auction auction);

    //-------  删  --



    //-------  改  --



    //-------  查  --
    Auction findByAid(Integer aid);

}
