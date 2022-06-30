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
public class AdminLogServiceImpl implements IAdminLogService {
    @Autowired
    private AdminLogMapper adminLogMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * 删除用户
     * @param adminLog 管理员操作日记
     */
    @Override
    public void DeleteUser(AdminLog adminLog) {
        User result_admin = userMapper.findByUid(adminLog.getAdminid()); //获取管理员ID
        if (result_admin==null){
            throw new UserNotExistException("当前用户不存在"); //判断ID是否存在
        }
        if (result_admin.getAdmin()!=1){
            throw new NoAdminPermissionException("没有管理员权限"); //判断是否为管理员
        }
        User result_user = userMapper.findByUid(adminLog.getUserid()); //获取要删除的用户信息
        if (result_user==null){
            throw new UserNotExistException("用户不存在"); //判断用户是否存在
        }
        Integer rows = userMapper.delete(adminLog.getUserid());
        /*判断是否执行成功*/
        if (rows==1){ //成功添加管理员操作记录
            adminLog.setUsername(result_user.getUsername());
            adminLog.setAdminname(result_admin.getUsername());
            adminLog.setUsercreatetime(result_user.getCreatetime());
            adminLog.setData("管理员删除用户");
            adminLogMapper.insert(adminLog);
        }else {//失败抛出异常
            throw new DeleteException("删除用户过程中发生了未知错误");
        }


    }
}
