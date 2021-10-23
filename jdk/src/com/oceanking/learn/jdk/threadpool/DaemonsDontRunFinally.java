package com.oceanking.learn.jdk.threadpool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class DaemonsDontRunFinally {

    public static void main(String[] args) {
        Object a = null;
        String call = (String) a;
        System.out.println(call);
        System.out.println(substring("abcd", 0, 3));
    }

    public static String substring(String str, int start, int end) {
        if (str == null) {
            return null;
        } else {
            if (end < 0) {
                end += str.length();
            }

            if (start < 0) {
                start += str.length();
            }

            if (end > str.length()) {
                end = str.length();
            }

            if (start > end) {
                return "";
            } else {
                if (start < 0) {
                    start = 0;
                }

                if (end < 0) {
                    end = 0;
                }

                return str.substring(start, end);
            }
        }
    }

    @Test
    public void test() {
        Thread t = new Thread(new ADaemon());

        t.setDaemon(false);

        t.start();
    }

}

class ADaemon implements Runnable {

    public void run() {

        try {

            System.out.println("start ADaemon...");

            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {

            System.out.println("Exiting via InterruptedException");
        } finally {

            System.out.println("This shoud be always run ?");
        }
    }

}