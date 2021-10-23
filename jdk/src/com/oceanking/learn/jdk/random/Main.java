package com.oceanking.learn.jdk.random;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * @author whaiy
 * @version 1.0 2019/01/18
 */
public class Main {

    private Random secureRandom = SecureRandom.getInstanceStrong();

    public Main() throws NoSuchAlgorithmException {
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        BigDecimal ax = new BigDecimal("1");

        if (ax.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("xxxx");
        }

        String vcode = new Main().getVcode();
        System.out.println(vcode);
        StringBuilder result = new StringBuilder();
        System.out.println(result.length());
        result.append("123");
        System.out.println(result.length());

        BigDecimal a = new BigDecimal("0.00");
        System.out.println(a.compareTo(BigDecimal.ZERO));
        System.out.println(a);
        System.out.println(BigDecimal.ZERO);
        System.out.println(BigDecimal.valueOf(0.00));
        int aa = 1;
        byte bb =(byte)aa;
        System.out.println(bb);


    }

    /**
     * 获取 6 位验证码
     *
     * @return
     */
    private String getVcode() {
        String vcode = secureRandom.nextInt(1000000) + "";
        if (vcode.length() != 6) {
            return getVcode();
        }
        return vcode;
    }

}
