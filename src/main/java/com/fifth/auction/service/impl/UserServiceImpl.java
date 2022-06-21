package com.fifth.auction.service.impl;

import com.fifth.auction.Utils.MD5Password;
import com.fifth.auction.emtity.User;
import com.fifth.auction.mapper.UserMapper;
import com.fifth.auction.service.IUserService;
import com.fifth.auction.service.ex.InserException;
import com.fifth.auction.service.ex.PasswordIncorrectException;
import com.fifth.auction.service.ex.UserNotExistException;
import com.fifth.auction.service.ex.UsernameDuplicatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    MD5Password md5Password =new MD5Password();
    //注册业务
    @Override
    public void reg(User user) {
        //判断用户名是否重复
        String username = user.getUsername();
        User result = userMapper.findByUsername(username);
        if (result!=null){
            throw new UsernameDuplicatedException("用户名被占用");
        }

        //密码加密
        //1.获取盐值

        String salt = md5Password.Salt();
        user.setSalt(salt);
        //2.MD5加密
        String oldPassword = user.getPassword();
        String newPassword = md5Password.getMD5password(oldPassword,salt);
        user.setPassword(newPassword);


        Integer rows = userMapper.insert(user);
        if (rows!=1){
            throw new InserException("用户注册过程中产生了未知的异常");
        }
    }

    @Override
    public User login(String username, String password) {

        User result = userMapper.findByUsername(username);

        if (result==null){
            throw new UserNotExistException("找不到该用户");
        }

        String salt = result.getSalt();
        String oldPasswod=result.getPassword();
        String newpassword=md5Password.getMD5password(password,salt);
        if (!newpassword.equals(oldPasswod)){
            throw new PasswordIncorrectException("用户密码错误");
        }

        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());

        return user;
    }
}
