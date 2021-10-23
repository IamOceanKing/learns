package com.oceanking.learn.jdk.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author whaiy
 * @version 1.0 2019/01/15
 */
public class Test {

    private static final List<String> MARITAL_STATUS = new ArrayList<>();

    public static void main(String[] args) {
        MARITAL_STATUS.add("2");
        MARITAL_STATUS.add("3");
        Random random = new Random();
      //  for(int i =0;i<100;i++)


         System.out.println(MARITAL_STATUS.get(3));
    }

}
