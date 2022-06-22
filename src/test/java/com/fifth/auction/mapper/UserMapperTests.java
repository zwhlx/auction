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
        user.setUsername("zhangsan111");
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
    @Test
    public void updatePasswordByUid(){
        String password = "1234";
        Integer rows = userMapper.updatePasswordByUid(11,password);
        System.out.println(rows);
    }
    @Test
    public void findByUid(){
        System.out.println(userMapper.findByUid(11));
    }
    @Test
    public void updateIntoByUid(){
        User user = new User();
        user.setUid(11);
        user.setGender(1);
//        user.setMobilephone("13333333333");
//        user.setAddress("广西省");
//        user.setEmail("test@mail.com");
        Integer rows = userMapper.updateIntoByUid(user);
        System.out.println(rows);
    }

    @Test
    public void delete(){
        Integer rows = userMapper.delete(14);
        System.out.println(rows);
    }

}
