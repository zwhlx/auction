package com.fifth.auction.Controller;

import com.fifth.auction.Utils.JSONResult;
import com.fifth.auction.service.ex.*;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.rmi.ServerException;

/** 控制层类的基类 */
public class BaseController {
    /** 操作成功的状态码 */
    public static final int OK = 200;

    // 请求处理方法，这个方法的返回值就是需要传递给前端的数据
    // 自动将异常对象传递给此方法的参数列表上
    //当前项目中产生了异常，被统一拦截到此方法中，这个方法此时就充当的是请求处理方法，方法的返回值直接给到前端
    @ExceptionHandler(ServiceException.class) //用于统一处理抛出的异常
    public JSONResult<Void> handleException(Throwable e){
        JSONResult<Void> result = new JSONResult<>(e);
        if (e instanceof UsernameDuplicatedException){
            result.setState(4000);
            result.setMessage("用户名被占用的异常");
        } else if (e instanceof UserNotExistException) {
            result.setState(5001);
            result.setMessage("用户数据不存在的异常");
        } else if (e instanceof PasswordIncorrectException) {
            result.setState(5002);
            result.setMessage("用户密码不正确的异常");
        } else if (e instanceof InserException){
            result.setState(5000);
            result.setMessage("用户注册过程中产生了未知的异常");
        }
        return result;
    }
}
