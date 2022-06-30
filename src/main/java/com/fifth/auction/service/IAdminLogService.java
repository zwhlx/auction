package com.fifth.auction.service;


import com.fifth.auction.emtity.AdminLog;

public interface IAdminLogService {
    /**
     * 删除用户
     * @param adminLog 管理员操作日记
     */
    void DeleteUser(AdminLog adminLog);
}
