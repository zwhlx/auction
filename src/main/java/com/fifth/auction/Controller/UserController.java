package com.fifth.auction.Controller;

import com.fifth.auction.Utils.JSONResult;
import com.fifth.auction.emtity.User;
import com.fifth.auction.service.IUserService;
import com.fifth.auction.service.ex.InserException;
import com.fifth.auction.service.ex.UsernameDuplicatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
