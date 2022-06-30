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

    /**
     * 判断是否已登录
     * @param session session
     * @return JSONResult，用户名和用户ID
     */
    @RequestMapping("islogin")
    public JSONResult<User> islogin(HttpSession session) {
        User user = new User(); //new一个User对象
        Object uid = session.getAttribute("uid"); //获取session中的uid
        if (uid==null) return new JSONResult<>(OK,user); //如果session中uid为空返回空数据
        user.setUsername(getUsernameFromSession(session)); //获取session中的用户名
        user.setUid(getUidFromSession(session)); //获取session中的用户id
        return new JSONResult<>(OK,user); //返回用户信息中的用户ID和用户名
    }

    /**
     * 用户注册
     * @param user 用户信息
     * @return JSONResult
     */
    @RequestMapping("reg")
    public JSONResult<Void> reg(User user) {
//        System.out.println(user);
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


    /**
     * 用户登录
     * @param username 用户名
     * @param password 用户密码
     * @param session session
     * @return JSONResult
     */
    @RequestMapping("login")
    public JSONResult<User> login(String username , String password, HttpSession session){
        /* 进行登录*/
        User data = userService.login(username, password);

        /*登录成功后将用户ID和用户名写入session*/
        session.setAttribute("uid",data.getUid());
        session.setAttribute("username",data.getUsername());

        return new JSONResult<>(OK,data);
    }

    /**
     * 用户登出
     * @param session session
     * @return JSONResult
     */
    @RequestMapping("logout")
    public JSONResult<User> logout(HttpSession session){
        /*清空session*/
        session.invalidate();
        return new JSONResult<>(OK);
    }

    /**
     * 更新密码
     * @param oldpassword 旧密码
     * @param newpassword 新密码
     * @param session session
     * @return JSONResult
     */
    @RequestMapping("update_password")
    public JSONResult<Void> UpdatePassword(String oldpassword,String newpassword,HttpSession session){
        /*通过session获取用户id和用户名*/
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);

        /*进行密码更新*/
        userService.UpdatePassword(uid,username,oldpassword,newpassword);
        return new JSONResult<>(OK);
    }

    /**
     * 更新用户信息
     * @param user 用户信息
     * @param session session
     * @return JSONResult
     */
    @RequestMapping("update_info")
    public JSONResult<Void> UpdateInfo(User user , HttpSession session){
        if (user.getEmail()==null && user.getGender()==null && user.getAddress()==null && user.getMobilephone()==null){//判断所有的值是否为空
            return new JSONResult<>(OK);
        }
        /*通过seesion获取用户id*/
        Integer uid = getUidFromSession(session);
        userService.UpdataInfo(uid,user);//更新用户信息
        return new JSONResult<>(OK);
    }

    /**
     * 获取用户信息
     * @param session session
     * @return JSONResult
     */
    @RequestMapping("get_info")
    public JSONResult<User> getUserInfo(HttpSession session){
        Integer uid = getUidFromSession(session);
        User data=userService.getUserInfo(uid);
        return new JSONResult<>(OK,data);
    }

    /**
     * 获取所有用户
     * @param session session
     * @return JSONResult
     */
    @RequestMapping("get_all")
    public JSONResult<ArrayList<User>> getAll(HttpSession session){
        Integer uid = getUidFromSession(session);
        ArrayList<User> data=userService.getAll(uid);
        return new JSONResult<>(OK,data);
    }

}
