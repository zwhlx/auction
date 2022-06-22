package com.fifth.auction.controller;

import com.fifth.auction.Utils.JSONResult;
import com.fifth.auction.emtity.AdminLog;
import com.fifth.auction.service.IAdminLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("admin")
public class AdminController extends BaseController{
    @Autowired
    private IAdminLogService adminLogService;

    @RequestMapping("user_delete")
    public JSONResult<Void> UpdateInfo(Integer uid, HttpSession session){
        Integer adminuid = getUidFromSession(session);
        AdminLog adminLog = getAdminLog();
        adminLog.setUserid(uid);
        adminLog.setAdminid(adminuid);
        adminLogService.DeleteUser(adminLog);
        return new JSONResult<>(OK);
    }
}
