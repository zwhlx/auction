package com.fifth.auction.mapper;

import com.fifth.auction.emtity.AdminLog;
import com.fifth.auction.emtity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AdminLogMapperTests {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AdminLogMapper adminLogMapper;

    @Test
    public void insert(){
        AdminLog adminLog = new AdminLog();
        adminLog.setUsername("zhangsan111");
        adminLog.setUsercreatetime(new Date());
        adminLog.setAdminid(11);
        adminLog.setAdminname("admin");
        adminLog.setUserid(14);
        adminLog.setData("tset测试");
        Integer rows = adminLogMapper.insert(adminLog);
        System.out.println(rows);
    }



}
