package com.company;

public class Bucket {
    private int size;
    private int time;

    public Bucket(int time,int size) {
        this.size = size;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Bucket{" +
                "size=" + size +
                ", time=" + time +
                '}';
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
