package com.oceanking.learn.jdk.exception;

import java.net.SocketTimeoutException;

/**
 * @author whaiy
 * @version 1.0 2018/12/26
 */
public class ExceptionTest {

    public static void main(String[] args) {
        System.out.println(new SocketTimeoutException());
        try {
            throw new RuntimeException(new SocketTimeoutException());
        } catch (Exception e) {
            Throwable cause = e.getCause();
            if (null instanceof SocketTimeoutException) {
                System.out.println(e);
            }else{
                System.out.println("------");
            }
        }
    }

}
