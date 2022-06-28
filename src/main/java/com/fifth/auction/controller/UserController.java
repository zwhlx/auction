package com.fifth.auction.controller;

import com.fifth.auction.Utils.JSONResult;
import com.fifth.auction.emtity.User;
import com.fifth.auction.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@RestController
@RequestMapping("users")
public class UserController extends BaseController{
    @Autowired
    private IUserService userService;

    @RequestMapping("islogin")
    public JSONResult<User> islogin(HttpSession session) {
        User user = new User();
        Object uid = session.getAttribute("uid");
        if (uid==null) return new JSONResult<>(OK,user);
        user.setUsername(getUsernameFromSession(session));
        user.setUid(getUidFromSession(session));
        return new JSONResult<>(OK,user);
    }

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
    @RequestMapping("logout")
    public JSONResult<User> logout(HttpSession session){
        session.invalidate();
        return new JSONResult<>(OK);
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
        if (user.getEmail()==null && user.getGender()==null && user.getAddress()==null && user.getMobilephone()==null){
            return new JSONResult<>(OK);
        }
        Integer uid = getUidFromSession(session);
        userService.UpdataInfo(uid,user);
        return new JSONResult<>(OK);
    }

    @RequestMapping("get_info")
    public JSONResult<User> getUserInfo(HttpSession session){
        Integer uid = getUidFromSession(session);
        User data=userService.getUserInfo(uid);
        return new JSONResult<>(OK,data);
    }

    @RequestMapping("get_all")
    public JSONResult<ArrayList<User>> getAll(HttpSession session){
        Integer uid = getUidFromSession(session);
        ArrayList<User> data=userService.getAll(uid);
        return new JSONResult<>(OK,data);
    }

}
