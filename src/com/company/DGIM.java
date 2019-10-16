package com.company;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class DGIM {
    private Deque<Bucket> queue = new LinkedList<>();
    private int size;
    public static int number;//序号，用于计算时间戳，窗口每读一个元素序号就加1

    public DGIM(int size){
        this.size = size;
    }

    public Deque<Bucket> getQueue() {
        return queue;
    }

    //查询窗口中的总数
    public int getContent(){
        Iterator<Bucket> iterator = this.queue.iterator();
        int result = 0;
        int curValue = 0;
        long startTime = this.queue.iterator().next().getTime();
        while(iterator.hasNext()){
            Bucket curBucket = iterator.next();
            if((startTime-curBucket.getTime())<this.size){
                curValue = curBucket.getSize();
                result+=curValue;
            }else{
                break;
            }
        }
        result -= curValue/2;
        return result;
    }


    //添加bucket
    public void add(){
        Bucket bucket = new Bucket(DGIM.number,1);
        queue.addFirst(bucket);
        if((bucket.getTime()-queue.getLast().getTime())>this.size){
            queue.removeLast();
        }
        //添加bucket时就要判断是否需要合并
        if(needMerge(this.queue.iterator())){
            merge(this.queue.iterator());
        }
    }




    //合并两个bucket
    public void merge(Iterator<Bucket> iterator){
        iterator.next();
        Bucket next = iterator.next();
        int firstValue = next.getSize();
        Bucket nextToNext = iterator.next();
        next.setSize(next.getSize()+nextToNext.getSize());
        iterator.remove();

        //一直合并到所有桶都不需要合并,判断是否需要合并的遍历的开始节点为头节点的下一个，而不是头节点
        Iterator<Bucket> iterator1 = getIterator(firstValue);
        if(needMerge(iterator1)){
            Iterator<Bucket> iterator2 = getIterator(firstValue);
            merge(getIterator(firstValue));
        }
    }


    //获取size=value的桶的遍历器
    public Iterator<Bucket> getIterator(int value){
        Iterator<Bucket> iterator = this.queue.iterator();
        while(iterator.hasNext()){
            Bucket next = iterator.next();
            if(next.getSize()==value){
                return iterator;
            }
        }
        return null;
    }



    //判断当前窗口中是否需要合并
    public boolean needMerge(Iterator<Bucket> iterator){
        if(iterator.hasNext()){
            Bucket head = iterator.next();
            if(iterator.hasNext()){
                Bucket next = iterator.next();
                if(next.getSize()!=head.getSize()){
                    return false;
                }else{
                    if(iterator.hasNext()){
                        Bucket nextToNext = iterator.next();
                        if(next.getSize()==nextToNext.getSize()){
                            return true;
                        }else{
                            return false;
                        }
                    }else{
                        return false;
                    }
                }
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}
