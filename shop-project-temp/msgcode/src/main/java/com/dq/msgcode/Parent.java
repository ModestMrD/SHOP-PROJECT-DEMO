package com.dq.msgcode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author DuQian
 * @Date 2019/3/22
 */
public class Parent {
    int x = 0;
    public  class Runner implements Runnable{
        @Override
        public void run() {
            int current = 0;
            for (int i = 0;i<4;i++){
                current = x;
                System.out.println(current+",");
                x = current + 2;
            }
        }
    }

    public static void main(String[] args) {
        new Parent().go();
    }

    private void go() {
        Runnable runnable = new Runner();
        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}
class child{
    public static void main(String[] args) {
        String nums = change("zeroonetwothreefourfivesixeightnine");
        System.out.println(nums);
    }
    public static String change(String arr) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("zero", "0");
        map.put("one", "1");
        map.put("two", "2");
        map.put("three", "3");
        map.put("four", "4");
        map.put("five", "5");
        map.put("six", "6");
        map.put("seven", "7");
        map.put("eight", "8");
        map.put("nine", "9");
        String message = "";
        while (!"".equals(arr)) {
            if (arr.startsWith("on") || arr.startsWith("tw") || arr.startsWith("si")) {
                message += map.get(arr.substring(0, 3));
                arr = arr.substring(3, arr.length());
            }
            if (arr.startsWith("ze") || arr.startsWith("fo") || arr.startsWith("fi") || arr.startsWith("ni")) {
                message += map.get(arr.substring(0, 4));
                arr = arr.substring(4, arr.length());
            }
            if (arr.startsWith("th") || arr.startsWith("se") || arr.startsWith("ei")) {
                message += map.get(arr.substring(0, 5));
                arr = arr.substring(5, arr.length());
            }
        }
        return message;
    }
}
