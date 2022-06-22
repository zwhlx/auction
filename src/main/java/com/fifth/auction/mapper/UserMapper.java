package com.fifth.auction.mapper;

import com.fifth.auction.emtity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    //增-----------
    Integer insert(User user);

    //删-----------

    Integer delete(Integer uid);

    //改------------
    Integer updatePasswordByUid(Integer uid,String password);

    Integer updateIntoByUid(User user);

    //查------------
    User findByUsername(String username);

    User findByUid(Integer uid);



}
