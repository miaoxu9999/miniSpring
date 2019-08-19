package com.mooc.mx;

import com.mooc.mx.starter.MiniApplication;

/**
 * @ClassName com.mooc.mx.Application
 * @Description TODO
 * @Author miaoxu
 * @Date 2019/8/17 21:43
 * @Version 1.0
 **/
public class Application {
    public static void main(String[] args) {
        System.out.println("Hello World");
        MiniApplication.run(Application.class, args);
    }
}
