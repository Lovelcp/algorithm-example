package com.wooyoo.algorithm.searching;

public class BinarySearchST<Key extends Comparable<Key>, Value> {

    private Key[] keys;
    private Value[] vals;
    private int N;

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    private void resize(int capacity) {

    }

    public Value get(Key key) {
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            return vals[i];
        }
        else {
            return null;
        }
    }

    /**
     * 返回小于给定键的键的数量
     *
     * @param key
     * @return
     */
    public int rank(Key key) {
        int low = 0, high = N - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) {
                // key 小于 keys[mid]
                high = mid - 1;
            }
            else if (cmp > 0) {
                low = mid + 1;
            }
            else {
                // 相等，刚好找到
                return mid;
            }
        }
        return low;
    }

    public void put(Key key, Value val) {
        // 查找键，找到则更新，否则则创建
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = val;
        }
        else {
            // 循环将之后的元素都往后移动一格
            for (int j = N; j > i; j--) {
                keys[j] = keys[j - 1];
                vals[j] = vals[j - 1];
            }
            keys[i] = key;
            vals[i] = val;
            N++; // 元素个数加1
        }
    }
}
