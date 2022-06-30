package com.fifth.auction.Utils;

import org.springframework.stereotype.Repository;
import org.springframework.util.DigestUtils;

import java.util.UUID;

@Repository
public class MD5Password {
    /**
     * 生成盐值
     * @return 盐值
     */
    public String Salt() {
        String salt=UUID.randomUUID().toString().toUpperCase();
        return salt;
    }

    /**
     * MD5加密
     * @param password 用户密码
     * @param salt 盐值
     * @return 处理后的密码
     */
    public String getMD5password(String password,String salt){
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }
        return password;
    }
}
