package com.fifth.auction.service.ex;

public class AuctionTimeException extends ServiceException{
    public AuctionTimeException() {
        super();
    }

    public AuctionTimeException(String message) {
        super(message);
    }

    public AuctionTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuctionTimeException(Throwable cause) {
        super(cause);
    }

    protected AuctionTimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
