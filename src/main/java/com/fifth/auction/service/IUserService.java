package com.fifth.auction.service;

import com.fifth.auction.emtity.User;

import java.util.ArrayList;


public interface IUserService {
    /**
     * 用户注册
     * @param user 用户信息
     */
    void reg(User user);

    /**
     * 用户登录
     * @param username 用户名
     * @param password 用户密码
     * @return 用户信息
     */
    User login(String username , String password);

    /**
     * 更新密码
     * @param uid 用户id
     * @param username 用户名
     * @param oldpassword 旧密码
     * @param newpassword 新密码
     */
    void UpdatePassword(Integer uid,String username,String oldpassword,String newpassword);

    /**
     * 更新用户信息
     * @param uid 用户id
     * @param user 用户信息
     */
    void UpdataInfo(Integer uid,User user);

    /**
     * 删除用户
     * @param uid 用户id
     */
    void DeleteUser(Integer uid);

    /**
     * 获取用户信息
     * @param uid 用户id
     * @return 用户信息
     */
    User getUserInfo(Integer uid);

    /**
     * 获取全部用户
     * @param uid 管理员用户id
     * @return 所有用户信息的ArrayList集合
     */
    ArrayList<User> getAll(Integer uid);
}
