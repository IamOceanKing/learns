package com.oceanking.learn.jdk.StringTokenizerTest;

import java.util.StringTokenizer;

/**
 * @author whaiy
 * @version 1.0 2019/01/25
 */
public class StringTokenizerTests {

    public static void main(String[] args) {
        StringTokenizer st = new StringTokenizer("www.baidu.com", ".");
        while(st.hasMoreElements()){
            System.out.println("Token:" + st.nextToken());
        }

        System.out.println('a'=='a');

    }

}
