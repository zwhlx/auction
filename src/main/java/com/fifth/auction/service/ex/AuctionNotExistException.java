package com.fifth.auction.service.ex;

/** 拍卖信息不存在的异常*/
public class AuctionNotExistException extends ServiceException{
    public AuctionNotExistException() {
        super();
    }

    public AuctionNotExistException(String message) {
        super(message);
    }

    public AuctionNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuctionNotExistException(Throwable cause) {
        super(cause);
    }

    protected AuctionNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
