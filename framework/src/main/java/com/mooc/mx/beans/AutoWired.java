package com.mooc.mx.beans;

import java.lang.annotation.*;

/**
 * @ClassName Bean
 * @Description TODO
 * @Author miaoxu
 * @Date 2019/8/19 14:29
 * @Version 1.0
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AutoWired {
}
