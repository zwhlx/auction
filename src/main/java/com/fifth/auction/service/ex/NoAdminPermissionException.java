package com.fifth.auction.service.ex;

/** 权限不够的异常 */
public class NoAdminPermissionException extends ServiceException{
    public NoAdminPermissionException() {
        super();
    }

    public NoAdminPermissionException(String message) {
        super(message);
    }

    public NoAdminPermissionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoAdminPermissionException(Throwable cause) {
        super(cause);
    }

    protected NoAdminPermissionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
