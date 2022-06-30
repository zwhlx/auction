package com.fifth.auction.service.ex;

/** 文件为空的异常 */
public class FileForNullException extends ServiceException{
    public FileForNullException() {
        super();
    }

    public FileForNullException(String message) {
        super(message);
    }

    public FileForNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileForNullException(Throwable cause) {
        super(cause);
    }

    protected FileForNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
