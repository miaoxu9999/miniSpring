package com.mooc.mx.starter;

import com.mooc.mx.beans.BeanFactory;
import com.mooc.mx.core.ClassScanner;
import com.mooc.mx.web.handler.HandlerManager;
import com.mooc.mx.web.server.TomcatServer;
import org.apache.catalina.LifecycleException;

import java.util.List;

/**
 * @ClassName MiniApplication
 * @Description TODO
 * @Author miaoxu
 * @Date 2019/8/17 21:51
 * @Version 1.0
 **/
public class MiniApplication {
    public static void run(Class<?> cls, String[] args){
        System.out.println("Hello mini Spring");
        TomcatServer tomcatServer = new TomcatServer(args);
        try {
            tomcatServer.startServer();
            List<Class<?>> classList = ClassScanner.scanClasses(cls.getPackage().getName());
            BeanFactory.initBean(classList);
            HandlerManager.resoloveMappingHandler(classList);
            classList.forEach(it -> System.out.println(it.getName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
