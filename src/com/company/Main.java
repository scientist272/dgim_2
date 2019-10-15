package com.company;

import java.io.*;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        // write your code here
        DGIM[] dgim = new DGIM[5];
        //从0到4分别代表2^4到2^0的位
        for (int i = 0; i < 5; i++) {
            dgim[i] = new DGIM(1000);
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("cmsc5741_stream_data2.txt")));) {
            while (true) {
                boolean isEnd = false;
                StringBuilder builder = new StringBuilder();
                int c1 = reader.read();
                builder.append((char) c1);
                while (true) {
                    int c2 = reader.read();
                    if ((char) c2 == ' ') {
                        break;
                    }
                    if (c2 == -1) {
                        isEnd = true;
                        break;
                    }
                    builder.append((char) c2);
                }
                if (isEnd) {
                    break;
                }
                char[] binary = Integer.toBinaryString(Integer.parseInt(builder.toString())).toCharArray();
                //寻找开始的位
                int start = 5 - binary.length;
                DGIM.number++;
                for (int i = 0; i < binary.length; i++) {
                    if (binary[i] == '1') {
                        dgim[start].add();
                    }
                    start++;
                }
            }
            Iterator<Bucket> iterator_16 = dgim[0].getQueue().iterator();
            Iterator<Bucket> iterator_8 = dgim[1].getQueue().iterator();
            Iterator<Bucket> iterator_4 = dgim[2].getQueue().iterator();
            Iterator<Bucket> iterator_2 = dgim[3].getQueue().iterator();
            Iterator<Bucket> iterator_1 = dgim[4].getQueue().iterator();


            while(iterator_16.hasNext()){
                System.out.println("Buckets for 16 :"+iterator_16.next());
            }
            System.out.println("There are "+dgim[0].getContent()+" times 16");
            while(iterator_8.hasNext()){
                System.out.println("Buckets for 8 :"+iterator_8.next());
            }
            System.out.println("There are "+dgim[1].getContent()+" times 8");
            while(iterator_4.hasNext()){
                System.out.println("Buckets for 4 :"+iterator_4.next());
            }
            System.out.println("There are "+dgim[2].getContent()+" times 4");
            while(iterator_2.hasNext()){
                System.out.println("Buckets for 2 :"+iterator_2.next());
            }
            System.out.println("There are "+dgim[3].getContent()+" times 2");

            while(iterator_1.hasNext()){
                System.out.println("Buckets for 1 :"+iterator_1.next());
            }
            System.out.println("There are "+dgim[4].getContent()+" times 1");

            int result = (dgim[0].getContent()*16 + dgim[1].getContent()*8
                    + dgim[2].getContent()*4 + dgim[3].getContent()*2 + dgim[4].getContent())/1000;
            System.out.println("Average price: "+result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
