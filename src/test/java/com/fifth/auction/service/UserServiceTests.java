package com.fifth.auction.service;

import com.fifth.auction.emtity.User;
import com.fifth.auction.mapper.UserMapper;
import com.fifth.auction.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {

    @Autowired
    private IUserService userService;

    @Test
    public void reg(){
        try {
            User user = new User();
            user.setUsername("li1");
            user.setPassword("123");
            userService.reg(user);
            System.out.println("OK");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }


    @Test
    public void login(){
        try {
            User user = userService.login("zxc1", "1234");
            System.out.println(user);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void UpdatePassword(){
        try {
            userService.UpdatePassword(5,"wang5","1234","123");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void UpdataInfo(){
        try {
            User user = new User();
            user.setGender(1);
            user.setEmail("test01@mail.com");
            user.setAddress("广东省");
            user.setMobilephone("13655555555");
            userService.UpdataInfo(11,user);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void DeleteUser(){
        try {
            userService.DeleteUser(13);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

}
