package com.fifth.auction.mapper;

import com.fifth.auction.emtity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UserMapper {

    //增-----------

    /**
     * 插入用户
     * @param user 用户信息
     * @return 影响行数
     */
    Integer insert(User user);

    //删-----------

    /**
     * 删除用户
     * @param uid 用户ID
     * @return 影响行数
     */
    Integer delete(Integer uid);

    //改------------

    /**
     * 改密码
     * @param uid 用户ID
     * @param password 用户密码
     * @return 影响行数
     */
    Integer updatePasswordByUid(Integer uid,String password);

    /**
     * 更新用户信息
     * @param user 用户信息
     * @return 影响行数
     */
    Integer updateIntoByUid(User user);

    //查------------

    /**
     * 根据用户名查找
     * @param username 用户名
     * @return 用户信息
     */
    User findByUsername(String username);

    /**
     * 根据用户ID查找
     * @param uid 用户ID
     * @return 用户信息
     */
    User findByUid(Integer uid);

    /**
     * 获取全部拍卖
     * @return 存有用户信息的ArrayList
     */
    ArrayList<User> getAll();



}
