package com.mooc.mx.web.server;

import com.mooc.mx.web.servlet.DispatcherServelet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;

/**
 * @ClassName TomcatServer
 * @Description TODO
 * @Author miaoxu
 * @Date 2019/8/18 10:13
 * @Version 1.0
 **/
public class TomcatServer {
    private Tomcat tomcat;
    private String[] args;

    public TomcatServer(String[] args){
        this.args = args;
    }

    public void startServer() throws LifecycleException {
        tomcat = new Tomcat();
        tomcat.setPort(6699);
        tomcat.start();

        Context context = new StandardContext();
        context.setPath("");
        context.addLifecycleListener(new Tomcat.FixContextListener());
        DispatcherServelet dispatcherServelet = new DispatcherServelet();
        Tomcat.addServlet(context, "dispatcherServelet", dispatcherServelet).setAsyncSupported(true);
        context.addServletMappingDecoded("/", "dispatcherServelet");
        tomcat.getHost().addChild(context);
        Thread awaitThread = new Thread("tomcat_await_thread"){
            @Override
            public void run(){
                TomcatServer.this.tomcat.getServer().await();
            }
        };
        awaitThread.setDaemon(false);
        awaitThread.start();
    }


}
