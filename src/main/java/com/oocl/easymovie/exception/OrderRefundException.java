package com.oocl.easymovie.exception;

public class OrderRefundException extends RuntimeException {

    public OrderRefundException() {
        super("Order Can not refund！");
    }
}
