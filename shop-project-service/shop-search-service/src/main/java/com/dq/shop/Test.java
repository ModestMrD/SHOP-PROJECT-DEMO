package com.dq.shop;

import java.util.Arrays;

/**
 * @author DuQian
 * @Date 2019/4/20
 */
public class Test {
    public static void main(String[] args) {
        int num = 100;
        int result = 0;
        int count = 0;
        while (count<=10){
            result = num + num/2;
            num = num/2;
            count++;
            System.out.println(result);
            System.out.println(num);
        }
    }
}
