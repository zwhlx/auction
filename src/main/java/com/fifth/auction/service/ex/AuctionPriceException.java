package com.fifth.auction.service.ex;

public class AuctionPriceException extends ServiceException{
    public AuctionPriceException() {
        super();
    }

    public AuctionPriceException(String message) {
        super(message);
    }

    public AuctionPriceException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuctionPriceException(Throwable cause) {
        super(cause);
    }

    protected AuctionPriceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
