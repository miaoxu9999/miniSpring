package com.mooc.mx.beans;

import com.mooc.mx.web.mvc.Controller;
import javafx.beans.binding.ObjectExpression;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName BeanFactory
 * @Description TODO
 * @Author miaoxu
 * @Date 2019/8/19 14:31
 * @Version 1.0
 **/
public class BeanFactory {
    private static Map<Class<?>, Object> classToBean = new ConcurrentHashMap<>();

    public static Object getBean(Class<?> cls){
        return classToBean.get(cls);
    }

    public static void initBean(List<Class<?>> classList) throws InstantiationException, IllegalAccessException,Exception {
        List<Class<?>> toCreate = new ArrayList<>(classList);

        while (toCreate.size() != 0){
            int remainSize = toCreate.size();
            for (int i = 0; i < toCreate.size(); i++) {
                if (finishCrete(toCreate.get(i))){
                    toCreate.remove(i);
                }
            }
            if (toCreate.size() == remainSize){
                throw new Exception("Cycle dependency");
            }
        }
        for (Map.Entry<Class<?>, Object> entry: classToBean.entrySet()) {
            System.out.println("entry的class文件为" + entry.getKey());
        }
    }

    private static boolean finishCrete(Class<?> cls) throws IllegalAccessException, InstantiationException {
        if (!cls.isAnnotationPresent(Bean.class) && !cls.isAnnotationPresent(Controller.class)){
            return true;
        }

        Object bean = cls.newInstance();
        for (Field field: cls.getDeclaredFields()){
            if (field.isAnnotationPresent(AutoWired.class)){
                Class<?> fieldType = field.getType();
                Object reliantBean = BeanFactory.getBean(fieldType);
                if (reliantBean == null){
                    return false;
                }
                field.setAccessible(true);
                field.set(bean, reliantBean);
            }
        }


        classToBean.put(cls, bean);
        return true;
    }

}
