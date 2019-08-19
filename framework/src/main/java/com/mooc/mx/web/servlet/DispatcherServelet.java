package com.mooc.mx.web.servlet;

import com.mooc.mx.web.handler.HandlerManager;
import com.mooc.mx.web.handler.MappingHandler;

import javax.servlet.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @ClassName DispatcherServelet
 * @Description TODO
 * @Author miaoxu
 * @Date 2019/8/18 10:22
 * @Version 1.0
 **/
public class DispatcherServelet implements Servlet {
    @Override
    public void init(ServletConfig config) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
//        res.getWriter().print("test");
        for (MappingHandler mappingHandler: HandlerManager.mappingHandlerList){
            try {
                if (mappingHandler.handle(req, res)){
                    return;
                }
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
