package com.fifth.auction.service.impl;

import com.fifth.auction.Utils.MD5Password;
import com.fifth.auction.emtity.User;
import com.fifth.auction.mapper.UserMapper;
import com.fifth.auction.service.IUserService;
import com.fifth.auction.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    MD5Password md5Password ;
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

        //执行语句
        Integer rows = userMapper.insert(user);
        if (rows!=1){//判断是否执行成功
            throw new InserException("用户注册过程中产生了未知的异常");
        }
    }

    /**
     * 用户登录
     * @param username 用户名
     * @param password 用户密码
     * @return 用户信息
     */
    @Override
    public User login(String username, String password) {

        User result = userMapper.findByUsername(username);

        if (result==null){//判断有无该用户
            throw new UserNotExistException("找不到该用户");
        }

        //获取用户盐值
        String salt = result.getSalt();
        String oldPasswod=result.getPassword();//数据库中的密码
        String newpassword=md5Password.getMD5password(password,salt); //登录时的密码
        if (!newpassword.equals(oldPasswod)){//判断是否一致
            throw new PasswordIncorrectException("用户密码错误");
        }

        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        //返回一个只有用户ID和用户名的user
        return user;
    }

    /**
     * 用户更新密码
     * @param uid 用户id
     * @param username 用户名
     * @param oldpassword 旧密码
     * @param newpassword 新密码
     */
    @Override
    public void UpdatePassword(Integer uid, String username, String oldpassword, String newpassword) {
        User result = userMapper.findByUid(uid);

        if (result == null ||!result.getUsername().equals(username)){//判断用户是否存在
            throw  new UserNotExistException("用户不存在");
        }


        String oldMd5Password = md5Password.getMD5password(oldpassword,result.getSalt());//旧密码加盐
        if (!oldMd5Password.equals(result.getPassword())){//判断旧密码是否正确
            throw new PasswordIncorrectException("密码错误");
        }

        String newMd5Password = md5Password.getMD5password(newpassword,result.getSalt());//新密码加盐

        Integer rows = userMapper.updatePasswordByUid(uid, newMd5Password);//更改数据库密码
        if (rows != 1) {//判断是否执行成功
            throw new UpdateException("更新失败,产生了未知异常");
        }
    }

    /**
     * 更新用户信息
     * @param uid 用户id
     * @param user 用户信息
     */
    @Override
    public void UpdataInfo(Integer uid, User user) {
        User result = userMapper.findByUid(uid);
        if (result == null)  {//判断用户是否存在
            throw new UserNotExistException("用户不存在");
        }
        user.setUid(uid);
        Integer rows = userMapper.updateIntoByUid(user);
        if (rows!=1){//判断语句是否执行成功
            throw new UpdateException("更新失败,产生了未知异常");
        }
    }

    /**
     * 删除用户
     * @param uid 用户id
     */
    @Override
    public void DeleteUser(Integer uid) {
        User result = userMapper.findByUid(uid);
        if (result == null)  {
            throw new UserNotExistException("用户不存在");
        }
        Integer rows = userMapper.delete(uid);
        if (rows!=1){
            throw new UpdateException("更新失败,产生了未知异常");
        }
    }

    /**
     * 获取用户信息
     * @param uid 用户id
     * @return user
     */
    @Override
    public User getUserInfo(Integer uid) {
        User result =userMapper.findByUid(uid);
        if (result == null)  {
            throw new UserNotExistException("用户不存在");
        }
        result.setPassword(null);
        result.setSalt(null);
        return result;
    }

    /**
     * 获取全部用户
     * @param uid 管理员用户id
     * @return 存有user的ArrayList
     */
    @Override
    public ArrayList<User> getAll(Integer uid) {
        User result=userMapper.findByUid(uid);
        if (result.getAdmin()!=1){
            throw new NoAdminPermissionException("没有权限");
        }
        return userMapper.getAll();
    }
}
