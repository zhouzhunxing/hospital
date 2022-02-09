package com.yjm.hospital.thread;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(3);
//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i) == 2) {
//                list.remove(i);
//            }
//        }
//        for (int i = list.size() - 1; i >= 0; i--) {
//            if (list.get(i) == 2) {
//                list.remove(i);
//            }
//        }

        System.out.println(list);
    }
}
