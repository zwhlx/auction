package com.fifth.auction.controller;

import com.fifth.auction.Utils.JSONResult;
import com.fifth.auction.emtity.User;
import com.fifth.auction.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("users")
public class UserController extends BaseController{
    @Autowired
    private IUserService userService;

    @RequestMapping("reg")
    public JSONResult<Void> reg(User user) {

            userService.reg(user);
            return new JSONResult<>(OK);
    }
//    @RequestMapping("reg")
//    public JSONResult<Void> reg(User user) {
//        JSONResult<Void> result = new JSONResult<>();
//        try {
//            userService.reg(user);
//            result.setState(200);
//            result.setMessage("用户注册成功");
//        }catch (UsernameDuplicatedException e){
//            result.setState(4000);
//            result.setMessage("用户名被占用");
//        }catch (InserException e){
//            result.setState(5000);
//            result.setMessage("用户注册过程中产生了未知的异常");
//        }return result;
//    }

    @RequestMapping("login")
    public JSONResult<User> login(String username , String password, HttpSession session){
        User data = userService.login(username, password);

        session.setAttribute("uid",data.getUid());
        session.setAttribute("username",data.getUsername());

        return new JSONResult<>(OK,data);
    }

    @RequestMapping("update_password")
    public JSONResult<Void> UpdatePassword(String oldpassword,String newpassword,HttpSession session){
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.UpdatePassword(uid,username,oldpassword,newpassword);
        return new JSONResult<>(OK);
    }

    @RequestMapping("update_info")
    public JSONResult<Void> UpdateInfo(User user , HttpSession session){
        Integer uid = getUidFromSession(session);
        userService.UpdataInfo(uid,user);
        return new JSONResult<>(OK);
    }

}
