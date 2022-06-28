package com.fifth.auction.service;

import com.fifth.auction.emtity.User;

import java.util.ArrayList;


public interface IUserService {
    void reg(User user);

    User login(String username , String password);

    void UpdatePassword(Integer uid,String username,String oldpassword,String newpassword);

    void UpdataInfo(Integer uid,User user);

    void DeleteUser(Integer uid);

    User getUserInfo(Integer uid);

    ArrayList<User> getAll(Integer uid);
}
