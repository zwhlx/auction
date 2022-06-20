package com.fifth.auction.mapper;

import com.fifth.auction.emtity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    Integer insert(User user);

    User findByUsername(String username);

}
