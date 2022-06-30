package com.fifth.auction.service.impl;

import com.fifth.auction.emtity.Auction;
import com.fifth.auction.emtity.User;
import com.fifth.auction.mapper.AuctionMapper;
import com.fifth.auction.mapper.UserMapper;
import com.fifth.auction.service.IAuctionService;
import com.fifth.auction.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Service
public class AuctionServiceImpl implements IAuctionService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AuctionMapper auctionMapper;

    /**
     * 添加拍卖品
     * @param auction 拍卖信息
     * @param file 拍卖品图片
     * @param session 用户session
     */
    @Override
    public void addAuction(Auction auction, MultipartFile file, HttpSession session) {


        if (file.isEmpty()) {
            throw new FileForNullException("上传文件为空");
        }

        // 获取当前项目的绝对磁盘路径
        String parent = session.getServletContext().getRealPath("upload");
        // 保存图片的文件夹
        File dir = new File(parent);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // 保存的头像文件的文件名
        String suffix = "";
        String originalFilename = file.getOriginalFilename();
        int beginIndex = originalFilename.lastIndexOf(".");
        if (beginIndex > 0) {
            suffix = originalFilename.substring(beginIndex);
        }
        String filename = UUID.randomUUID().toString() + suffix;
        // 创建文件对象，表示保存的图片
        File dest = new File(dir, filename);
        // 执行保存图片
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            // 抛出异常
            throw new FileUploadIOException("上传文件时读写错误，请稍后重尝试");
        }

        // 图片路径
        String img = "/upload/" + filename;

        auction.setImg(img);

        Date date = auction.getEndtime();
        if (date.before(new Date())){
            throw new AuctionTimeException("当前结束拍卖时间小于开始拍卖时间");
        }
        /*判断信息是否为空*/
        if (auction.getCategory()==null||
                auction.getOwnerid()==null||
                auction.getOwnername()==null||
                auction.getAuctionname()==null||
                auction.getEndtime()==null||
                auction.getStartprice()==null){
            throw new UpdateException("数据为空");
        }
        Integer ownerid = auction.getOwnerid();//发布者id
        User result = userMapper.findByUid(ownerid);
        if (result == null) {
            throw new UserNotExistException("用户不存在");
        }
        Integer rows = auctionMapper.insert(auction);
        if (rows!=1){//判断是否执行成功
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

    /**
     * 查找用户获得的拍卖品
     * @param currentserid 获得者ID
     * @return 存拍卖品的ArrayList
     */
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
        if (auction==null){throw new AuctionNotExistException("找不到拍卖品");}//查找拍卖品是否存在
        if (new Date().getTime()>=auction.getEndtime().getTime()||auction.getIsend()==0){//判断是否结束
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
        if (data==null){//判断拍卖是否存在
            throw new AuctionNotExistException("拍卖不存在");
        }
        if (data.getIsend()!=0||new Date().getTime()>=data.getEndtime().getTime()){//判断拍卖是否已结束
            throw new AuctionTimeException("拍卖已结束");
        }
        /*判断出价当前是否最高*/
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
