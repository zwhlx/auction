package com.fifth.auction.Utils;

import org.springframework.util.DigestUtils;

import java.util.UUID;

public class MD5Password {
    public String Salt() {
        String salt=UUID.randomUUID().toString().toUpperCase();
        return salt;
    }
    public String getMD5password(String password,String salt){
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }
        return password;
    }
}
