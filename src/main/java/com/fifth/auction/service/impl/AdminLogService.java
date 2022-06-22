package com.fifth.auction.service.impl;

import com.fifth.auction.emtity.AdminLog;
import com.fifth.auction.emtity.User;
import com.fifth.auction.mapper.AdminLogMapper;
import com.fifth.auction.mapper.UserMapper;
import com.fifth.auction.service.IAdminLogService;
import com.fifth.auction.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminLogService implements IAdminLogService {
    @Autowired
    private AdminLogMapper adminLogMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public void DeleteUser(AdminLog adminLog) {
        User result_admin = userMapper.findByUid(adminLog.getAdminid());
        if (result_admin==null){
            throw new UserNotExistException("当前用户不存在");
        }
        if (result_admin.getAdmin()!=1){
            throw new NoAdminPermissionException("没有管理员权限");
        }
        User result_user = userMapper.findByUid(adminLog.getUserid());
        if (result_user==null){
            throw new UserNotExistException("用户不存在");
        }
        Integer rows = userMapper.delete(adminLog.getUserid());
        if (rows==1){
            adminLog.setUsername(result_user.getUsername());
            adminLog.setAdminname(result_admin.getUsername());
            adminLog.setUsercreatetime(result_user.getCreatetime());
            adminLog.setData("管理员删除用户");
            adminLogMapper.insert(adminLog);
        }else {
            throw new DeleteException("删除用户过程中发生了未知错误");
        }


    }
}
