package com.mooc.mx.web.handler;

import com.mooc.mx.web.mvc.Controller;
import com.mooc.mx.web.mvc.RequestMapping;
import com.mooc.mx.web.mvc.RequestParam;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName HandlerManager
 * @Description TODO
 * @Author miaoxu
 * @Date 2019/8/18 16:15
 * @Version 1.0
 **/
public class HandlerManager {
    public static List<MappingHandler> mappingHandlerList = new ArrayList<>();


    public static void resoloveMappingHandler(List<Class<?>> classList){
        for (Class<?> cls: classList){
            if(cls.isAnnotationPresent(Controller.class)){
                parseHandlerFromController(cls);
            }
        }
    }

    private static void parseHandlerFromController(Class<?> cls){
        Method[] methods = cls.getMethods();
        for (Method method: methods){
            if (!method.isAnnotationPresent(RequestMapping.class)){
                continue;
            }
            String uri = method.getDeclaredAnnotation(RequestMapping.class).value();
            List<String> parameterList = new ArrayList<>();
            for (Parameter parameter: method.getParameters()){
                if (parameter.isAnnotationPresent(RequestParam.class)){
                    parameterList.add(parameter.getAnnotation(RequestParam.class).value());

                }
            }

            String[] params = parameterList.toArray(new String[parameterList.size()]);
            MappingHandler mappingHandler = new MappingHandler(uri, method, cls, params);
            HandlerManager.mappingHandlerList.add(mappingHandler);

        }
    }

}
