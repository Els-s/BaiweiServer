package com.els.baiwei;



/**
 * @Despt:
 * @Author: Els-s
 * @Time: 2019/11/13 12:20
 */
public class test1 {

    public static void main(String[] args) {
        method(null);
    }

    public static void method(String param) {
        switch (param) {
// 肯定不是进入这里
            case "sth":
                System.out.println("it's sth");
                break;
// 也不是进入这里
            case "null":
                System.out.println("it's null");
                break;
// 也不是进入这里
            default:
                System.out.println("default");
        }
        StringBuilder stringBuilder=new StringBuilder();

    }
}
