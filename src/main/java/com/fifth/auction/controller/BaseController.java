package com.fifth.auction.controller;

import com.fifth.auction.Utils.JSONResult;
import com.fifth.auction.emtity.AdminLog;
import com.fifth.auction.service.ex.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

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
            result.setMessage(e.getMessage());//"用户名被占用的异常"
        } else if (e instanceof UserNotExistException) {
            result.setState(5001);
            result.setMessage(e.getMessage());//"用户数据不存在的异常"
        } else if (e instanceof PasswordIncorrectException) {
            result.setState(5002);
            result.setMessage(e.getMessage());//"用户密码不正确的异常"
        } else if (e instanceof InserException){
            result.setState(5000);
            result.setMessage(e.getMessage());//"插入数据过程中产生了未知的异常"
        } else if (e instanceof UpdateException){
            result.setState(5003);
            result.setMessage(e.getMessage());//"更新数据时产生未知的异常"
        } else if (e instanceof DeleteException){
            result.setState(4001);
            result.setMessage(e.getMessage());//"删除数据时产生未知的异常"
        } else if (e instanceof NoAdminPermissionException) {
            result.setState(4002);
            result.setMessage(e.getMessage());//"没有管理员权限"
        } else if(e instanceof AuctionNotExistException){
            result.setState(6000);
            result.setMessage(e.getMessage());//拍卖信息不存在
        }else if(e instanceof AuctionPriceException){
            result.setState(6001);
            result.setMessage(e.getMessage());//拍卖价格的问题
        }else if(e instanceof AuctionTimeException){
            result.setState(6001);
            result.setMessage(e.getMessage());//拍卖时间的问题
        }
        return result;
    }

    /**
     * 获取当前登录用户的id
     * @param session session
     * @return 当前登录用户的UID
     */
    protected final Integer getUidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * 获取当前登录用户的username
     * @param session session
     * @return 当前登录用户的username
     */
    protected final String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }

    protected final AdminLog getAdminLog(){
        return new AdminLog();
    }
}
