package com.huaxu.minimybatis.algorithm.queue;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * @description:
 * @Author: Mr.Hua
 * @date: 2024/8/11 21:21
 */
public class LRUCache {

    private List<Node> priorityQueue;

    private TimeComparator timeComparator;

    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        priorityQueue = new ArrayList<>(capacity);
        timeComparator = new TimeComparator();
    }

    public int get(int key) {
        Collections.sort(priorityQueue, timeComparator);
        while (priorityQueue.size() > capacity) {
            priorityQueue.remove(0);
        }
        Integer value = null;
        Iterator<Node> iterator = priorityQueue.iterator();
        Node node = null;
        while (iterator.hasNext()){
            Node next = iterator.next();
            if (next.key == key){
                iterator.remove();
                value = next.val;
                node = new Node(next.key, next.val, next.time + 1);
                break;
            }
        }
        if (node != null){
            priorityQueue.add(node);
        }
        return value == null ? -1 : value;
    }

    public void put(int key, int value) {
        Collections.sort(priorityQueue, timeComparator);
        while (priorityQueue.size() > capacity) {
            priorityQueue.remove(0);
        }
        Iterator<Node> iterator = priorityQueue.iterator();
        Node node = null;
        while (iterator.hasNext()) {
            Node next = iterator.next();
            if (next.key == key) {
                next.val = value;
                next.time = next.time + 1;
                iterator.remove();
                node = new Node(next.key, next.val, next.time);
                break;
            }
        }
        if (node != null){
            priorityQueue.add(node);
        }else {
            priorityQueue.add(new Node(key, value, 1));
        }
    }

    class Node {
        int key, val, time;

        Node(int k, int v, int time) {
            this.key = k;
            this.val = v;
            this.time = time;
        }
    }

    class TimeComparator implements Comparator<Node> {
        @Override
        public int compare(@NotNull Node o1, @NotNull Node o2) {
            if (o1.time == o2.time) {
                return -1;
            }
            return o1.time - o2.time;
        }
    }


    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.get(1);
        lruCache.put(3, 3);
        lruCache.get(2);
        lruCache.put(4, 4);
        lruCache.get(1);
        int i = lruCache.get(3);
        int i1 = lruCache.get(4);
    }

}
