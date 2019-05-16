package com.dq.shopprojectindex.paike;

import java.util.Scanner;

/**
 * @author DuQian
 * @Date 2019/4/13
 */
public class TestDemo {
    public static void main(String[] args) {
        OrderCourse ordercourse = new OrderCourse();
        ordercourse.initShow();
        System.out.print("增加资源(以上陈列的)，请输入y，否则进行排课操作：");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        if (input.equals("y")) {
            ordercourse.add();
        }
        System.out.println("系统进行排课中...");
        ordercourse.control();

    }

}
