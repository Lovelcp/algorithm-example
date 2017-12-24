package com.wooyoo.algorithm.searching;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * 基于链表的符号表
 *
 * @param <Key>
 * @param <Value>
 */
public class SequentialSearchST<Key, Value> {
    private int size; // 链表长度
    private Node head; // 链表头节点

    /**
     * 链表的节点
     */
    private class Node {
        Key key;
        Value value;
        Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    /**
     * 返回符号表的大小
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 符号表是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 判断符号表里是否含有该key
     *
     * @param key
     * @return
     */
    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null");
        }
        return get(key) != null;
    }

    /**
     * 遍历链表，找到对应key的节点，输出Value，如果未找到，则返回null
     *
     * @param key
     * @return
     */
    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null");
        }
        Node node = head;
        while (node != null) {
            if (Objects.equals(node.key, key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    /**
     * 遍历链表，如果key已经存在，则更新对应节点的value，否则新建节点并且置为头结点
     * 特别的，如果value为null的话，则进行删除，因为符号表中不允许存在为null的value
     *
     * @param key
     * @param value
     */
    public void put(Key key, Value value) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null");
        }
        if (value == null) {
            delete(key);
            return;
        }
        Node node = head;
        while (node != null) {
            if (Objects.equals(node.key, key)) {
                node.value = value; // 命中，更新value
                return;
            }
            node = node.next;
        }

        head = new Node(key, value, head); // 未命中，新建节点
        size++;
    }

    /**
     * 从符号表中删除key以及对应的value，如果key不存在的话，则无影响
     *
     * @param key
     */
    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null");
        }

        Node current = head;
        Node prev = null;

        while (current != null) {
            if (Objects.equals(current.key, key)) {
                if (prev == null) {
                    head = current.next;
                }
                else {
                    prev.next = current.next; // 从链表中删除
                }
                size--; // 大小减1
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    /**
     * 返回符号表中所有的key
     *
     * @return
     */
    public Iterable<Key> keys() {
        List<Key> keys = new LinkedList<>();
        Node node = head;
        while (node != null) {
            keys.add(node.key);
            node = node.next;
        }
        return keys;
    }
}
