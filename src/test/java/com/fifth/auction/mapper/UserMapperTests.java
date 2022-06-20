package com.fifth.auction.mapper;

import com.fifth.auction.emtity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert(){
        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("123");
        Integer rows= userMapper.insert(user);
        System.out.println(rows);
    }

    @Test
    public void findByUsername(){
        String username = "zhangsan";
        User user = userMapper.findByUsername(username);
        System.out.println(user);
    }


}
