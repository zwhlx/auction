package com.fifth.auction.service;

import com.fifth.auction.emtity.AdminLog;
import com.fifth.auction.emtity.User;
import com.fifth.auction.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AdminLogServiceTests {

    @Autowired
    private IUserService userService;
    @Autowired
    private IAdminLogService adminLogService;

    @Test
    public void DeleteUser(){
        AdminLog adminLog = new AdminLog();
        adminLog.setUserid(1);
        adminLog.setAdminid(11);
        adminLogService.DeleteUser(adminLog);
    }

}
