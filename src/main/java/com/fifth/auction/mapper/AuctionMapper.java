package com.fifth.auction.mapper;

import com.fifth.auction.emtity.Auction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface AuctionMapper {
//-------  增  --------------------

    /**
     * 插入数据
     * @param auction 拍卖信息
     * @return 影响行数
     */
    Integer insert(Auction auction);

//-------  删  --------------------

    /**
     * 删除数据
     * @param aid 拍卖品ID
     * @return 影响行数
     */
    Integer delete(Integer aid);


//-------  改  -------------------

    /**
     * 更新拍卖状态
     * @param auction 拍卖信息
     * @return 影响行数
     */
    Integer updateIsend(Auction auction);

    /**
     * 更新最高出价者
     * @param auction 拍卖信息
     * @return 影响行数
     */
    Integer updateHighestBidder(Auction auction);



//-------  查  -------------------

    /**
     * 根据拍卖品ID查找拍卖信息
     * @param aid 拍卖品ID
     * @return 拍卖信息
     */
    Auction findByAid(Integer aid);

    /**
     * 获取全部拍卖
     * @return 存有拍卖信息的ArrayList
     */
    ArrayList<Auction> getAll();

    /**
     * 根据拍卖种类查找拍卖信息
     * @param category 种类
     * @return 存有拍卖信息的ArrayList
     */
    ArrayList<Auction> findByCategory(Integer category);

    /**
     * 根据拍卖状态查找
     * @param isend 拍卖状态
     * @return 存有拍卖信息的ArrayList
     */
    ArrayList<Auction> findByisend(Integer isend);

    /**
     * 根据拍卖者ID查找
     * @param ownerid 拍卖者ID
     * @return 存有拍卖信息的ArrayList
     */
    ArrayList<Auction> findByOwnerid(Integer ownerid);

    /**
     * 查找用户已拍卖到的拍卖品
     * @param isend 拍卖状态
     * @param currentserid 用户ID
     * @return 存有拍卖信息的ArrayList
     */
    ArrayList<Auction> findByisendCid(Integer isend,Integer currentserid);

}
