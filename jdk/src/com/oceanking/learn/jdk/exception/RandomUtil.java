package com.oceanking.learn.jdk.exception;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class RandomUtil {

    private static final String COMMON_DATE = "yyyyMMdd";

    private static Random random = new Random();

    public static String getRandomStr(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; ++i) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }

        return sb.toString();
    }

    public static int getRandomRange(int max, int min) {
        return (int) (random.nextInt() * (double) (max - min) + (double) min);
    }

    public static String getRandomString(int length, String base) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; ++i) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }

        return sb.toString();
    }

    public static String getRandomString(int length) {
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; ++i) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }

        return sb.toString();
    }

    public static String getRandomNumString(int length) {
        String base = "0123456789";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; ++i) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }

        return sb.toString();
    }

    public static int[] getRangRandom(int start, int end) {
        return getRangRandom(start, end, end - start + 1);
    }

    public static int[] getRangRandom(int start, int end, int num) {
        int length = end - start + 1;
        if (length >= 1 && num <= length) {
            int[] numbers = new int[length];
            int[] result = new int[num];

            for (int random = 0; random < length; ++random) {
                numbers[random] = random + start;
            }

            for (int i = 0; i < num; ++i) {
                int m = random.nextInt(length - i) + i;
                result[i] = numbers[m];
                int temp = numbers[m];
                numbers[m] = numbers[i];
                numbers[i] = temp;
            }

            return result;
        } else {
            return null;
        }
    }

    public static String code() {
        Set set = GetRandomNumber();
        Iterator iterator = set.iterator();

        String temp;
        for (temp = ""; iterator.hasNext(); temp = temp + iterator.next()) {
            ;
        }

        return temp;
    }

    public static Set<Integer> GetRandomNumber() {
        HashSet set = new HashSet();
        while (set.size() < 6) {
            set.add(random.nextInt(10));
        }

        return set;
    }

    /**
     * 产生 15 位的唯一序列号
     *
     * @return
     */
    public static String getSerialNumber_15() {
        int hashCode = UUID.randomUUID().toString().hashCode();
        if (hashCode < 0) {
            hashCode = -hashCode;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(COMMON_DATE);
        return (sdf.format(new Date()).substring(2, 8)
                + String.format("%010d", hashCode)).substring(0, 15);
    }

}
