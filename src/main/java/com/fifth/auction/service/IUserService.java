package com.fifth.auction.service;

import com.fifth.auction.emtity.User;


public interface IUserService {
    void reg(User user);

    User login(String username , String password);
}
