package com.oceanking.learn.jdk.collection;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 王海洋
 * @version 1.0 2018/12/10
 */
public class LearnArrayBlockingQueue {

    public static void main(String[] args) throws InterruptedException {
        LearnArrayBlockingQueue o = new LearnArrayBlockingQueue();

        Executors.newFixedThreadPool(1);
        ArrayBlockingQueue queue = new ArrayBlockingQueue(2);
        queue.put("a");
        queue.put("b");
        Object take = queue.take();
        System.out.println(take);

        Object peek = queue.peek();

        take = queue.take();
        System.out.println(take);

        System.out.println(peek);
    }

    @Test
    public void test() throws InterruptedException {
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(2);
        linkedBlockingQueue.put("a");
        linkedBlockingQueue.put("b");
    }

    @Test
    public void test1() throws InterruptedException {
        SynchronousQueue synchronousQueue = new SynchronousQueue();
        synchronousQueue.put("a");
        synchronousQueue.put("b");
    }

    @Test
    public void test2() throws InterruptedException {
        PriorityBlockingQueue<Example> priorityBlockingQueue = new PriorityBlockingQueue();
        Example e1 = new Example("a", "a1");
        Example e2 = new Example("b", "b");
        Example e3 = new Example("a", "a2");
        Example e4 = new Example("a", "a3");
        priorityBlockingQueue.put(e1);
        priorityBlockingQueue.put(e2);
        priorityBlockingQueue.put(e3);
        priorityBlockingQueue.put(e4);

        Example take = priorityBlockingQueue.take();
        System.out.println(take.getDesc());
        take = priorityBlockingQueue.take();
        System.out.println(take.getDesc());
        take = priorityBlockingQueue.take();
        System.out.println(take.getDesc());
    }

    static class Example implements Comparable<Example> {

        String value;

        String desc;

        public Example(String value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        @Override
        public int compareTo(Example o) {
            return value.compareTo(o.value);
        }

        public String getDesc() {
            return desc;
        }

    }

}
